package com.github.fashionbrot.core.service.impl;



import com.github.fashionbrot.common.req.SysOrgReq;
import com.github.fashionbrot.common.util.ConvertUtil;
import com.github.fashionbrot.common.vo.PageVo;
import com.github.fashionbrot.common.vo.RespVo;
import com.github.fashionbrot.core.entity.SysOrgEntity;
import com.github.fashionbrot.core.mapper.SysOrgMapper;
import com.github.fashionbrot.core.service.SysOrgService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 机构表
 *
 * @author fashionbrot
 * @email fashionbrot@163.com
 * @date 2021-03-27
 */
@Service
public class SysOrgServiceImpl  extends BaseServiceImpl<SysOrgMapper, SysOrgEntity> implements SysOrgService {

    @Autowired
    private SysOrgMapper sysOrgMapper;

    @Override
    public Object pageReq(SysOrgReq req) {
        Page<?> page = PageHelper.startPage(req.getPageNum(),req.getPageSize());
        Map<String,Object> map = ConvertUtil.toMap(req);
        List<SysOrgEntity> listByMap = baseMapper.selectByMap(map);

        return RespVo.success(PageVo.builder()
                .rows(listByMap)
                .total(page.getTotal())
                .build());
    }

}