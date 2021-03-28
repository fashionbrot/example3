package com.github.fashionbrot.core.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 菜单-角色关系表
 *
 * @author fashionbrot
 * @email fashionbrot@163.com
 * @date 2021-03-28
 */
@Data
@ApiModel(value = "菜单-角色关系表")
public class SysMenuRoleRelationDTO implements Serializable {


	@ApiModelProperty(value = "自增id")
	private Long id;

	@ApiModelProperty(value = "用户ID")
	private Long menuId;

	@ApiModelProperty(value = "角色ID")
	private Long roleId;

}