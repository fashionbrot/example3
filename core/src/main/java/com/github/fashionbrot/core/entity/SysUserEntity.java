package com.github.fashionbrot.core.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户表
 *
 * @author fashionbrot
 * @email fashionbrot@163.com
 * @date 2021-03-27
 */
@ApiModel(value = "系统用户表")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("sys_user")
public class SysUserEntity implements Serializable {

	private static final long serialVersionUID = 3273628163345558645L;

	@ApiModelProperty(value = "自增ID")
	@TableId(type = IdType.AUTO)
	private Long id;


	@ApiModelProperty(value = "用户名")
	@TableField("user_name")
	private String userName;


	@ApiModelProperty(value = "真实姓名")
	@TableField("real_name")
	private String realName;


	@ApiModelProperty(value = "身份证号")
	@TableField("id_card")
	private String idCard;


	@ApiModelProperty(value = "加密密码")
	@TableField("password")
	private String password;


	@ApiModelProperty(value = "密码加盐参数")
	@TableField("salt")
	private String salt;


	@ApiModelProperty(value = "用户状态")
	@TableField("status")
	private Integer status;


	@ApiModelProperty(value = "是否是超级管理员 1超级 0普通")
	@TableField("super_admin")
	private Integer superAdmin;


	@ApiModelProperty(value = "最后登录时间")
	@TableField("last_login_time")
	private Date lastLoginTime;


	@ApiModelProperty(value = "创建者id")
	@TableField("create_id")
	private Long createId;


	@ApiModelProperty(value = "创建时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@TableField(value="create_date",fill = FieldFill.INSERT)
	private Date createDate;


	@ApiModelProperty(value = "最近更新者id")
	@TableField("update_id")
	private Long updateId;


	@ApiModelProperty(value = "最近更新时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@TableField(value="update_date",fill = FieldFill.UPDATE)
	private Date updateDate;


	@ApiModelProperty(value = "删除标志位 1删除 0未删除")
	@TableLogic(value = "0", delval = "1")
	@TableField(value = "del_flag",fill = FieldFill.INSERT)
	private Integer delFlag;



}