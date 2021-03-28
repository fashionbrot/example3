package com.github.fashionbrot.core.service.impl;


import com.github.fashionbrot.common.req.SysRoleReq;
import com.github.fashionbrot.common.util.ConvertUtil;
import com.github.fashionbrot.common.vo.PageVo;
import com.github.fashionbrot.common.vo.RespVo;
import com.github.fashionbrot.core.entity.SysRoleEntity;
import com.github.fashionbrot.core.mapper.SysRoleMapper;
import com.github.fashionbrot.core.service.SysRoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 角色表
 *
 * @author fashionbrot
 * @email fashionbrot@163.com
 * @date 2021-03-28
 */
@Service
public class SysRoleServiceImpl  extends BaseServiceImpl<SysRoleMapper, SysRoleEntity> implements SysRoleService {



    @Override
    public Object pageReq(SysRoleReq req) {
        Page<?> page = PageHelper.startPage(req.getPageNum(),req.getPageSize());
        Map<String,Object> map = ConvertUtil.toMap(req);
        List<SysRoleEntity> listByMap = baseMapper.selectByMap(map);

        return PageVo.builder()
                .rows(listByMap)
                .total(page.getTotal())
                .build();
    }

}