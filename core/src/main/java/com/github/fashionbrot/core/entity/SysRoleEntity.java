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
 * 角色表
 *
 * @author fashionbrot
 * @email fashionbrot@163.com
 * @date 2021-03-28
 */
@ApiModel(value = "角色表")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("sys_role")
public class SysRoleEntity implements Serializable {



	@ApiModelProperty(value = "自增ID")
	@TableId(type = IdType.AUTO)
	private Long id;

	@ApiModelProperty(value = "角色描述")
	@TableField("role_name")
	private String roleName;

	@TableField(exist = false)
	private  String menuIds;


}