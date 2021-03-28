package com.github.fashionbrot.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * 菜单-角色关系表
 *
 * @author fashionbrot
 * @email fashionbrot@163.com
 * @date 2021-03-28
 */
@ApiModel(value = "菜单-角色关系表")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("sys_menu_role_relation")
public class SysMenuRoleRelationEntity implements Serializable {



	@ApiModelProperty(value = "自增id")
	@TableId(type = IdType.AUTO)
	private Long id;

	@ApiModelProperty(value = "用户ID")
	@TableField("menu_id")
	private Long menuId;

	@ApiModelProperty(value = "角色ID")
	@TableField("role_id")
	private Long roleId;


}