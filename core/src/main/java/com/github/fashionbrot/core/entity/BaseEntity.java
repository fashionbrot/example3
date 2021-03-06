package com.github.fashionbrot.core.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体类
 */
@Data
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = -1720420392839192028L;

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField(value = "create_id",fill = FieldFill.INSERT)
    private Long createId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value="create_date",fill = FieldFill.INSERT)
    private Date createDate;

    @TableField(value = "update_id",fill = FieldFill.UPDATE)
    private Long updateId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "update_date",fill = FieldFill.UPDATE)
    private Date updateDate;


    @TableLogic(value = "0", delval = "1")
    @TableField(value = "del_flag",fill = FieldFill.INSERT)
    private int delFlag;

}
