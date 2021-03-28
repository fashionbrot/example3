package com.github.fashionbrot.core.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.fashionbrot.common.consts.GlobalConst;
import com.github.fashionbrot.common.enums.RespCode;
import com.github.fashionbrot.common.exception.MarsException;
import com.github.fashionbrot.common.model.LoginModel;
import com.github.fashionbrot.common.req.SysUserReq;
import com.github.fashionbrot.common.util.ConvertUtil;
import com.github.fashionbrot.common.util.CookieUtil;
import com.github.fashionbrot.common.util.JwtTokenUtil;
import com.github.fashionbrot.common.util.PasswordUtil;
import com.github.fashionbrot.common.vo.PageVo;
import com.github.fashionbrot.common.vo.RespVo;
import com.github.fashionbrot.core.entity.SysRoleEntity;
import com.github.fashionbrot.core.entity.SysUserEntity;
import com.github.fashionbrot.core.mapper.SysRoleMapper;
import com.github.fashionbrot.core.mapper.SysUserMapper;
import com.github.fashionbrot.core.service.SysRoleService;
import com.github.fashionbrot.core.service.SysUserService;
import com.github.fashionbrot.core.service.UserLoginService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.RoleInfo;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 系统用户表
 *
 * @author fashionbrot
 * @email fashionbrot@163.com
 * @date 2021-03-27
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUserEntity> implements SysUserService {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private UserLoginService userLoginService;

    @Override
    public Object pageReq(SysUserReq req) {
        Page<?> page = PageHelper.startPage(req.getPageNum(),req.getPageSize());
        Map<String,Object> map = ConvertUtil.toMap(req);
        List<SysUserEntity> listByMap = baseMapper.selectByMap(map);

        return PageVo.builder()
                .rows(listByMap)
                .total(page.getTotal())
                .build();
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object doLogin(SysUserReq req) {

        SysUserEntity userInfo = baseMapper.selectOne(new QueryWrapper<SysUserEntity>().eq("user_name", req.getUsername()));
        if (userInfo == null) {
            throw new MarsException(RespCode.USER_OR_PASSWORD_IS_ERROR);
        }
        if (userInfo.getStatus() == 0) {
            throw new MarsException("用户已关闭");
        }
        String salt = userInfo.getSalt();
        String encryptPassword = PasswordUtil.encryptPassword(req.getPassword(), salt);
        if (!userInfo.getPassword().equals(encryptPassword)) {
            throw new MarsException(RespCode.USER_OR_PASSWORD_IS_ERROR);
        }

        userInfo.setLastLoginTime(new Date());
        baseMapper.updateById(userInfo);


        SysRoleEntity role = null;
        if (userInfo.getRoleId()!=null){
            role = sysRoleService.getById(userInfo.getRoleId());
        }

        String roleName ="";
        Long roleId = null;
        if (role!=null){
            roleName =role.getRoleName();
            roleId = role.getId();
        }

        String token = JwtTokenUtil.createToken(userInfo.getId(),userInfo.getRealName(),roleId,roleName,userInfo.getSuperAdmin()==1);
        CookieUtil.setCookie(request,response,userInfo.getRealName(),roleName,token,false);

        return RespVo.success();
    }

    @Override
    public void insert(SysUserEntity entity) {
        String salt = PasswordUtil.getSalt();
        String password = PasswordUtil.encryptPassword(entity.getPassword(),salt);
        entity.setSalt(salt);
        entity.setPassword(password);
        int count= baseMapper.selectCount(new QueryWrapper<SysUserEntity>().eq("user_name",entity.getUserName()));
        if (count>0){
            throw new MarsException("用户名已存在，请重新输入");
        }
        int result= baseMapper.insert(entity);
        if (entity.getId()!=null && entity.getId().intValue()!=0) {
            if (entity.getSuperAdmin()==null){
                SysRoleEntity roleInfo=sysRoleMapper.selectById(entity.getRoleId());
                if (roleInfo==null){
                    throw new MarsException("当前角色不存在，请刷新重试");
                }
            }
        }
        if (result!=1){
            throw new MarsException(RespCode.SAVE_ERROR);
        }
    }

    @Override
    public void edit(SysUserEntity userInfo) {
        SysUserEntity oldUser = baseMapper.selectById(userInfo.getId());
        if (oldUser != null && !oldUser.getPassword().equals(userInfo.getPassword())) {
            String salt = PasswordUtil.getSalt();
            String password = PasswordUtil.encryptPassword(userInfo.getPassword(), salt);
            userInfo.setSalt(salt);
            userInfo.setPassword(password);
        }
        if (userInfo.getSuperAdmin()==null){
            SysRoleEntity roleInfo=sysRoleMapper.selectById(userInfo.getRoleId());
            if (roleInfo==null){
                throw new MarsException("当前角色不存在，请刷新重试");
            }
        }
        SysUserEntity user= baseMapper.selectOne(new QueryWrapper<SysUserEntity>().eq("user_name",userInfo.getUserName()));
        if (user!=null && !userInfo.getUserName().equals(user.getUserName())){
            throw new MarsException("用户名已存在，请重新输入");
        }
        int result= baseMapper.updateById(userInfo);
        if (result!=1){
            throw new MarsException(RespCode.UPDATE_ERROR);
        }
    }

    @Override
    public void resetPwd(String pwd, String newPwd) {
        if (pwd.equals(newPwd)) {
            throw new MarsException("新密码和原密码一致，请修改");
        }
        LoginModel login = userLoginService.getLogin();

        SysUserEntity user = baseMapper.selectById(login.getUserId());
        if (user != null) {
            String salt =user.getSalt();
            String encryptPassword = PasswordUtil.encryptPassword(pwd, salt);
            if (!user.getPassword().equals(encryptPassword)) {
                throw new MarsException("原密码输入错误，请重新输入");
            }


            String password =PasswordUtil.encryptPassword(newPwd,salt);
            user.setPassword(password);
            int result = baseMapper.updateById(user);
            if (result != 1) {
                throw new MarsException(RespCode.UPDATE_ERROR);
            }
        }
    }
}