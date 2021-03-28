
package com.github.fashionbrot.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.fashionbrot.core.entity.SysMenuEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜单表
 *
 * @author fashionbrot
 * @email fashionbrot@163.com
 * @date 2021-03-27
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenuEntity> {

    List<SysMenuEntity> loadRoleMenu(Long roleId);


}