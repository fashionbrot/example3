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
import java.util.*;

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
    private HttpServletRequest request;

    @Override
    public Object pageReq(SysMenuReq req) {
        Page<?> page = PageHelper.startPage(req.getPageNum(),req.getPageSize());
        Map<String,Object> map = ConvertUtil.toMap(req);
        List<SysMenuEntity> listByMap = baseMapper.selectByMap(map);

        return PageVo.builder()
                .rows(listByMap)
                .total(page.getTotal())
                .build();
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
            String permission = "";
            MarsPermission classAnnotation = method.getDeclaringClass().getAnnotation(MarsPermission.class);
            if (classAnnotation!=null){
                permission = classAnnotation.value();
            }
            // 判断接口是否需要登录
            MarsPermission methodAnnotation = method.getAnnotation(MarsPermission.class);
            if (methodAnnotation != null  ) {
                permission = permission+ methodAnnotation.value();
            }

            return checkPermission(permission,model.getRoleId());
        }

        return false;

    }

    public boolean checkPermission(String permission,Long roleId){
        QueryWrapper<SysMenuEntity> q = new QueryWrapper<>();
        q.select("count(1)");
        q.last(" inner join sys_menu_role_relation b on b.menu_id = menu_id" +
                "where b.role_id = "+roleId+"  ");
        Integer count = baseMapper.selectCount(q);
        if (count>0){
            return true;
        }
        return false;
    }

    @Override
    public List<SysMenuEntity> loadRoleMenu(Long roleId, boolean superAdmin) {
        List<SysMenuEntity> list = null;
        if (superAdmin){
            list = baseMapper.selectList(new QueryWrapper<SysMenuEntity>().in("menu_level",Arrays.asList(1,2)));
        }else{
            list =  baseMapper.loadRoleMenu(roleId);
        }

        Map<String, Boolean> checkedMap = new HashMap<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (SysMenuEntity mm : list) {
                checkedMap.put(mm.getId().toString(), true);
            }
        }
        if (CollectionUtils.isNotEmpty(list)) {
            list = loadChildMenu(list, checkedMap);
        }
        return list;
    }

    private List<SysMenuEntity> loadChildMenu(List<SysMenuEntity> menuBarList, Map<String, Boolean> checkedMap) {
        if (CollectionUtils.isNotEmpty(menuBarList)) {
            List<SysMenuEntity> menuList = new ArrayList<>(15);
            for (SysMenuEntity m : menuBarList) {
                if (m.getMenuLevel() != 1) {
                    continue;
                }
                m.setChildMenu(loadChildMenu(menuBarList, m, checkedMap));
                menuList.add(m);
            }
            return menuList;
        }
        return null;
    }

    private List<SysMenuEntity> loadChildMenu(List<SysMenuEntity> menuBarList, SysMenuEntity parentMenu, Map<String, Boolean> checkedMap) {
        if (CollectionUtils.isNotEmpty(menuBarList)) {
            List<SysMenuEntity> menuList = new ArrayList<>(10);
            for (SysMenuEntity m : menuBarList) {
                if (Objects.equals(m.getParentMenuId(), parentMenu.getId())) {
                    menuList.add(m);
                }
            }
            return menuList;
        }
        return null;
    }

    @Override
    public List<SysMenuEntity> queryListAll() {
        QueryWrapper<SysMenuEntity> queryWrapper=new QueryWrapper();
        queryWrapper.orderByAsc("priority ");
        List<SysMenuEntity> menuBarList = baseMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(menuBarList)) {
            for (SysMenuEntity m : menuBarList) {
                if (m.getMenuLevel() != 1) {
                    m.setParentMenuName(parentMenuName(menuBarList, m.getParentMenuId()));
                }
            }
        }

        return menuBarList;
    }
    private String parentMenuName(List<SysMenuEntity> menuBarList, Long parentMenuId) {
        if (CollectionUtils.isNotEmpty(menuBarList)) {
            for (SysMenuEntity m : menuBarList) {
                if (m.getMenuLevel() == 1 && Objects.equals(m.getId(), parentMenuId)) {
                    return m.getMenuName();
                }else if (m.getMenuLevel() == 2 && Objects.equals(m.getId(), parentMenuId)) {
                    return m.getMenuName();
                }
            }
        }
        return "";
    }
}