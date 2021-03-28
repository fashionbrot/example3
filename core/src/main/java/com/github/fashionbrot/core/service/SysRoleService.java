package com.github.fashionbrot.core.service;

import com.github.fashionbrot.common.req.SysRoleReq;
import com.github.fashionbrot.core.entity.SysRoleEntity;

/**
 * 角色表
 *
 * @author fashionbrot
 * @email fashionbrot@163.com
 * @date 2021-03-28
 */
public interface SysRoleService  extends BaseService<SysRoleEntity> {

    Object pageReq(SysRoleReq req);

    void edit(SysRoleEntity entity);

    void add(SysRoleEntity entity);

    void deleteById(Long id);
}