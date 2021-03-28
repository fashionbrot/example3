package com.github.fashionbrot.core.service.impl;


import com.github.fashionbrot.common.req.SysLogReq;
import com.github.fashionbrot.common.util.ConvertUtil;
import com.github.fashionbrot.common.vo.PageVo;
import com.github.fashionbrot.common.vo.RespVo;
import com.github.fashionbrot.core.entity.SysLogEntity;
import com.github.fashionbrot.core.mapper.SysLogMapper;
import com.github.fashionbrot.core.service.SysLogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 系统日志
 *
 * @author fahsionbrot
 * @email fashionbrot@163.com
 * @date 2021-03-27
 */
@Service
public class SysLogServiceImpl extends BaseServiceImpl<SysLogMapper, SysLogEntity> implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public Object pageReq(SysLogReq req) {
        Page<?> page = PageHelper.startPage(req.getPageNum(),req.getPageSize());
        Map<String,Object> map = ConvertUtil.toMap(req);
        List<SysLogEntity> listByMap = baseMapper.selectByMap(map);

        return PageVo.builder()
                .rows(listByMap)
                .total(page.getTotal())
                .build();
    }

}