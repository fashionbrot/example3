package com.github.fashionbrot.core.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.fashionbrot.common.req.SysRoleReq;
import com.github.fashionbrot.common.util.ConvertUtil;
import com.github.fashionbrot.common.vo.PageVo;
import com.github.fashionbrot.common.vo.RespVo;
import com.github.fashionbrot.core.entity.SysMenuRoleRelationEntity;
import com.github.fashionbrot.core.entity.SysRoleEntity;
import com.github.fashionbrot.core.mapper.SysMenuRoleRelationMapper;
import com.github.fashionbrot.core.mapper.SysRoleMapper;
import com.github.fashionbrot.core.service.SysRoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 角色表
 *
 * @author fashionbrot
 * @email fashionbrot@163.com
 * @date 2021-03-28
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class SysRoleServiceImpl  extends BaseServiceImpl<SysRoleMapper, SysRoleEntity> implements SysRoleService {

    @Autowired
    private SysMenuRoleRelationMapper sysMenuRoleRelationMapper;


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


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SysRoleEntity entity) {
        baseMapper.insert(entity);
        updateRoleMenu(entity.getId(),entity.getMenuIds());
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void edit(SysRoleEntity entity) {
        baseMapper.updateById(entity);
        updateRoleMenu(entity.getId(),entity.getMenuIds());
    }


    public void updateRoleMenu(Long roleId, String ids) {
        sysMenuRoleRelationMapper.delete(new QueryWrapper<SysMenuRoleRelationEntity>().eq("role_id", roleId));
        if (StringUtils.isNotEmpty(ids)) {
            String[] menuIds = ids.split(",");
            for (String menuId : menuIds) {
                sysMenuRoleRelationMapper.insert(SysMenuRoleRelationEntity.builder()
                        .roleId(roleId)
                        .menuId(Long.valueOf(menuId))
                        .build());
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        baseMapper.deleteById(id);
        sysMenuRoleRelationMapper.delete(new QueryWrapper<SysMenuRoleRelationEntity>().eq("role_id", id));
    }
}