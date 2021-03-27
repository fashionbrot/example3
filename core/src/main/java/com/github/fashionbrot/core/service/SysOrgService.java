package com.github.fashionbrot.core.service;


import com.github.fashionbrot.common.req.SysOrgReq;
import com.github.fashionbrot.core.entity.SysOrgEntity;

/**
 * 机构表
 *
 * @author fashionbrot
 * @email fashionbrot@163.com
 * @date 2021-03-27
 */
public interface SysOrgService  extends BaseService<SysOrgEntity> {

    Object pageReq(SysOrgReq req);

}