package com.github.fashionbrot.core.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 角色表
 *
 * @author fashionbrot
 * @email fashionbrot@163.com
 * @date 2021-03-28
 */
@Data
@ApiModel(value = "角色表")
public class SysRoleDTO implements Serializable {


	@ApiModelProperty(value = "自增ID")
	private Long id;

	@ApiModelProperty(value = "角色描述")
	private String roleName;

}