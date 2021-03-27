package com.github.fashionbrot.core.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 系统日志
 *
 * @author fahsionbrot
 * @email fashionbrot@163.com
 * @date 2021-03-27
 */
@Data
@ApiModel(value = "系统日志")
public class SysLogDTO implements Serializable {

	private static final long serialVersionUID = -4316168057011366959L;

	@ApiModelProperty(value = "自增id")
	private Long id;

	@ApiModelProperty(value = "请求描述")
	private String requestDesc;

	@ApiModelProperty(value = "请求url")
	private String requestUrl;

	@ApiModelProperty(value = "请求方法")
	private String requestMethod;

	@ApiModelProperty(value = "请求ip")
	private String requestIp;

	@ApiModelProperty(value = "请求参数")
	private String requestParam;

	@ApiModelProperty(value = "接口方法")
	private String targetMethod;

	@ApiModelProperty(value = "1 请求日志 2:异常日志")
	private Integer logType;

	@ApiModelProperty(value = "接口类型 1:后台日志 2:机构后台日志 3:web 端日志")
	private Integer interfaceType;

	@ApiModelProperty(value = "创建者id")
	private Long createId;

	@ApiModelProperty(value = "操作者")
	private String createName;

	@ApiModelProperty(value = "创建时间")
	private Date createDate;

	@ApiModelProperty(value = "异常日志")
	private String exception;

	@ApiModelProperty(value = "删除标志位 1删除 0未删除")
	private Integer delFlag;

}