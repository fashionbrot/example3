package com.github.fashionbrot.core.service;

import com.github.fashionbrot.common.req.SysLogReq;
import com.github.fashionbrot.core.entity.SysLogEntity;

/**
 * 系统日志
 *
 * @author fahsionbrot
 * @email fashionbrot@163.com
 * @date 2021-03-27
 */
public interface SysLogService extends BaseService<SysLogEntity> {

    Object pageReq(SysLogReq req);

}