package com.github.fashionbrot.core.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 系统用户表
 *
 * @author fashionbrot
 * @email fashionbrot@163.com
 * @date 2021-03-27
 */
@Data
@ApiModel(value = "系统用户表")
public class SysUserDTO implements Serializable {


	@ApiModelProperty(value = "自增ID")
	private Long id;

	@ApiModelProperty(value = "用户名")
	private String userName;

	@ApiModelProperty(value = "真实姓名")
	private String realName;

	@ApiModelProperty(value = "身份证号")
	private String idCard;

	@ApiModelProperty(value = "加密密码")
	private String password;

	@ApiModelProperty(value = "密码加盐参数")
	private String salt;

	@ApiModelProperty(value = "用户状态")
	private Integer status;

	@ApiModelProperty(value = "是否是超级管理员 1超级 0普通")
	private Integer superAdmin;

	@ApiModelProperty(value = "最后登录时间")
	private Date lastLoginTime;

	@ApiModelProperty(value = "创建者id")
	private Long createId;

	@ApiModelProperty(value = "创建时间")
	private Date createDate;

	@ApiModelProperty(value = "最近更新者id")
	private Long updateId;

	@ApiModelProperty(value = "最近更新时间")
	private Date updateDate;

	@ApiModelProperty(value = "删除标志位 1删除 0未删除")
	private Integer delFlag;

	@ApiModelProperty(value = "角色id")
	private Long roleId;
}