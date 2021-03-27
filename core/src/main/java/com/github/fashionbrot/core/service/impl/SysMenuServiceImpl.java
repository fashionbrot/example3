package com.github.fashionbrot.core.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.github.fashionbrot.common.annotation.MarsPermission;
import com.github.fashionbrot.common.model.LoginModel;
import com.github.fashionbrot.common.req.SysMenuReq;
import com.github.fashionbrot.common.util.ConvertUtil;
import com.github.fashionbrot.common.vo.PageVo;
import com.github.fashionbrot.common.vo.RespVo;
import com.github.fashionbrot.core.entity.SysMenuEntity;
import com.github.fashionbrot.core.mapper.SysMenuMapper;
import com.github.fashionbrot.core.service.SysMenuService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * 菜单表
 *
 * @author fashionbrot
 * @email fashionbrot@163.com
 * @date 2021-03-27
 */
@SuppressWarnings("ALL")
@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuMapper, SysMenuEntity> implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private HttpServletRequest request;

    @Override
    public Object pageReq(SysMenuReq req) {
        Page<?> page = PageHelper.startPage(req.getPageNum(),req.getPageSize());
        Map<String,Object> map = ConvertUtil.toMap(req);
        List<SysMenuEntity> listByMap = baseMapper.selectByMap(map);

        return RespVo.success(PageVo.builder()
                .rows(listByMap)
                .total(page.getTotal())
                .build());
    }

    @Override
    public boolean checkPermission(Object handler, LoginModel model) {

        if (handler instanceof HandlerMethod) {
            //如果是超级管理员
            if (model!=null && model.isSuperAdmin()){
                return true;
            }

            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();

            // 判断接口是否需要登录
            MarsPermission methodAnnotation = method.getAnnotation(MarsPermission.class);
            if (methodAnnotation != null  ) {
                List<SysMenuEntity> menuBarList = getMenus(model);
                if (CollectionUtils.isNotEmpty(menuBarList)) {
                    for (SysMenuEntity m : menuBarList) {
                        //验证菜单是否有权限
                        if ( (m.getMenuLevel() == 2 || m.getMenuLevel() == 3) && m.getCode().equals(methodAnnotation.value())) {
                            return true;
                        }
                    }
                }
                return false;
            }
            return true;
        }

        return false;

    }

    private List<SysMenuEntity> getMenus(LoginModel model) {
        return baseMapper.selectList(null);
    }

}