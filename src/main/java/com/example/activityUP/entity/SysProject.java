package com.example.activityUP.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDate;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 培训项目表
 * </p>
 *
 * @author 劳威锟
 * @since 2023-03-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
//@ApiModel(value="SysProject对象", description="培训项目表")
public class SysProject implements Serializable {

    private static final long serialVersionUID = 1L;

    //(value = "培训项目id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //(value = "培训项目名称")
    private String name;

    //(value = "创建时间")
    private Date createTime;

    //(value = "更新时间")
    private Date updateTime;

    //(value = "小时数")
    private Double hours;

    //(value = "学分")
    private Double credit;

    //(value = "创建者id")
    private Long createUserId;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" , timezone = "GMT+8")
    private LocalDate beginTime;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" , timezone = "GMT+8")
    private LocalDate endTime;
}
