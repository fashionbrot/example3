package com.github.fashionbrot.core.service;

import com.github.fashionbrot.common.req.SysMenuRoleRelationReq;
import com.github.fashionbrot.core.entity.SysMenuRoleRelationEntity;

/**
 * 菜单-角色关系表
 *
 * @author fashionbrot
 * @email fashionbrot@163.com
 * @date 2021-03-28
 */
public interface SysMenuRoleRelationService  extends BaseService<SysMenuRoleRelationEntity> {

    Object pageReq(SysMenuRoleRelationReq req);

}