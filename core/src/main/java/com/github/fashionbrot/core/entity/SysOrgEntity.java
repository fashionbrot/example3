package com.github.fashionbrot.core.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 机构表
 *
 * @author fashionbrot
 * @email fashionbrot@163.com
 * @date 2021-03-27
 */
@ApiModel(value = "机构表")
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sys_org")
public class SysOrgEntity implements Serializable {


	private static final long serialVersionUID = 6662343156091943632L;

	@ApiModelProperty(value = "自增ID")
	@TableId(type = IdType.AUTO)
	private Long id;


	@ApiModelProperty(value = "机构名称")
	@TableField("org_name")
	private String orgName;


	@ApiModelProperty(value = "父机构id")
	@TableField("parent_id")
	private Long parentId;


	@ApiModelProperty(value = "组id")
	@TableField("group_id")
	private Long groupId;


	@ApiModelProperty(value = "负责人")
	@TableField("leader")
	private String leader;


	@ApiModelProperty(value = "联系电话")
	@TableField("phone")
	private String phone;


	@ApiModelProperty(value = "机构状态 1开启 2关闭")
	@TableField("status")
	private Integer status;


	@ApiModelProperty(value = "创建时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@TableField(value="create_date",fill = FieldFill.INSERT)
	private Date createDate;


	@ApiModelProperty(value = "最近更新时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@TableField(value="update_date",fill = FieldFill.UPDATE)
	private Date updateDate;



}