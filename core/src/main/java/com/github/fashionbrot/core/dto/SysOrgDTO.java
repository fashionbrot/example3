package com.github.fashionbrot.core.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 机构表
 *
 * @author fashionbrot
 * @email fashionbrot@163.com
 * @date 2021-03-27
 */
@Data
@ApiModel(value = "机构表")
public class SysOrgDTO implements Serializable {

	private static final long serialVersionUID = 8182090902705164125L;

	@ApiModelProperty(value = "自增ID")
	private Long id;

	@ApiModelProperty(value = "机构名称")
	private String orgName;

	@ApiModelProperty(value = "父机构id")
	private Long parentId;

	@ApiModelProperty(value = "组id")
	private Long groupId;

	@ApiModelProperty(value = "负责人")
	private String leader;

	@ApiModelProperty(value = "联系电话")
	private String phone;

	@ApiModelProperty(value = "机构状态 1开启 2关闭")
	private Integer status;

	@ApiModelProperty(value = "创建时间")
	private Date createDate;

	@ApiModelProperty(value = "最近更新时间")
	private Date updateDate;

}