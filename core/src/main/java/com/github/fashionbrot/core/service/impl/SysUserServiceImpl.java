package com.github.fashionbrot.core.service.impl;


import com.github.fashionbrot.common.consts.GlobalConst;
import com.github.fashionbrot.common.enums.RespCode;
import com.github.fashionbrot.common.exception.MarsException;
import com.github.fashionbrot.common.model.LoginModel;
import com.github.fashionbrot.common.req.SysUserReq;
import com.github.fashionbrot.common.util.ConvertUtil;
import com.github.fashionbrot.common.util.CookieUtil;
import com.github.fashionbrot.common.util.JwtTokenUtil;
import com.github.fashionbrot.common.vo.PageVo;
import com.github.fashionbrot.common.vo.RespVo;
import com.github.fashionbrot.core.entity.SysUserEntity;
import com.github.fashionbrot.core.mapper.SysUserMapper;
import com.github.fashionbrot.core.service.SysUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 系统用户表
 *
 * @author fashionbrot
 * @email fashionbrot@163.com
 * @date 2021-03-27
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUserEntity> implements SysUserService {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private SysUserMapper sysUserMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private HttpServletRequest request;

    @Override
    public Object pageReq(SysUserReq req) {
        Page<?> page = PageHelper.startPage(req.getPageNum(),req.getPageSize());
        Map<String,Object> map = ConvertUtil.toMap(req);
        List<SysUserEntity> listByMap = baseMapper.selectByMap(map);

        return RespVo.success(PageVo.builder()
                .rows(listByMap)
                .total(page.getTotal())
                .build());
    }

    @Override
    public LoginModel getLogin() {
        String token = CookieUtil.getCookieValue(request, GlobalConst.AUTH_KEY, false);
        if (StringUtils.isEmpty(token)) {
            throw new MarsException(RespCode.SIGNATURE_MISMATCH);
        }
        LoginModel model = JwtTokenUtil.getLogin(token);
        if (model!=null){
            request.setAttribute(GlobalConst.AUTH_KEY,model);
        }
        return model;
    }

    @Override
    public LoginModel getSafeLogin() {
        try {
            Object attribute = request.getAttribute(GlobalConst.AUTH_KEY);
            if (attribute==null) {
                return getLogin();
            }
            return (LoginModel)attribute;
        }catch (Exception e){
        }
        return null;
    }
}