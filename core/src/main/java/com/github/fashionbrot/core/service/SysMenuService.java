package com.github.fashionbrot.core.service;

import com.github.fashionbrot.common.model.LoginModel;
import com.github.fashionbrot.common.req.SysMenuReq;
import com.github.fashionbrot.common.vo.RespVo;
import com.github.fashionbrot.core.entity.SysMenuEntity;

import java.util.List;
import java.util.Map;

/**
 * 菜单表
 *
 * @author fashionbrot
 * @email fashionbrot@163.com
 * @date 2021-03-27
 */
public interface SysMenuService extends BaseService<SysMenuEntity> {

    Object pageReq(SysMenuReq req);

    boolean checkPermission(Object handler, LoginModel model);

    List<SysMenuEntity> loadRoleMenu(Long roleId, boolean superAdmin);

    List<SysMenuEntity> queryListAll();

    List<SysMenuEntity> loadAllMenu(Long roleId, Integer isShowCode);
}