package com.github.fashionbrot.core.service;

import com.github.fashionbrot.common.model.LoginModel;
import com.github.fashionbrot.common.req.SysUserReq;
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

    LoginModel getLogin();

    LoginModel getSafeLogin();
}