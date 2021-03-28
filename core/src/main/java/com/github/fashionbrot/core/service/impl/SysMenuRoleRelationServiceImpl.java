package com.github.fashionbrot.core.service.impl;


import com.github.fashionbrot.common.req.SysMenuRoleRelationReq;
import com.github.fashionbrot.common.util.ConvertUtil;
import com.github.fashionbrot.common.vo.PageVo;
import com.github.fashionbrot.common.vo.RespVo;
import com.github.fashionbrot.core.entity.SysMenuRoleRelationEntity;
import com.github.fashionbrot.core.mapper.SysMenuRoleRelationMapper;
import com.github.fashionbrot.core.service.SysMenuRoleRelationService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 菜单-角色关系表
 *
 * @author fashionbrot
 * @email fashionbrot@163.com
 * @date 2021-03-28
 */
@Service
public class SysMenuRoleRelationServiceImpl  extends BaseServiceImpl<SysMenuRoleRelationMapper, SysMenuRoleRelationEntity> implements SysMenuRoleRelationService {


    @Override
    public Object pageReq(SysMenuRoleRelationReq req) {
        Page<?> page = PageHelper.startPage(req.getPageNum(),req.getPageSize());
        Map<String,Object> map = ConvertUtil.toMap(req);
        List<SysMenuRoleRelationEntity> listByMap = baseMapper.selectByMap(map);

        return PageVo.builder()
                .rows(listByMap)
                .total(page.getTotal())
                .build();
    }

}