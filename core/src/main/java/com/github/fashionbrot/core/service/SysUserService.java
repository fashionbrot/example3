package com.github.fashionbrot.core.service;

import com.github.fashionbrot.common.req.SysUserReq;
import com.github.fashionbrot.common.vo.RespVo;
import com.github.fashionbrot.core.entity.SysUserEntity;

/**
 * 系统用户表
 *
 * @author fashionbrot
 * @email fashionbrot@163.com
 * @date 2021-03-27
 */
public interface SysUserService extends BaseService<SysUserEntity> {

    Object pageReq(SysUserReq req);

    Object doLogin(SysUserReq req);

    void insert(SysUserEntity entity);

    void edit(SysUserEntity entity);

    void resetPwd(String oldPassword, String newPassword);
}