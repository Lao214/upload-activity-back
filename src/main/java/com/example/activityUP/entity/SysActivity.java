package com.example.activityUP.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

/**
 * <p>
 * 活动资料
 * </p>
 *
 * @author 劳威锟
 * @since 2023-03-15
 */
@Getter
@Setter
@ExcelIgnoreUnannotated
@ContentRowHeight(21)
@HeadRowHeight(30)
@ColumnWidth(15)
@FieldNameConstants
@EqualsAndHashCode(callSuper = false)
//@ApiModel(value="SysActivity对象", description="活动资料")
public class SysActivity implements Serializable {

    private static final long serialVersionUID = 1L;

    //(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //(value = "参与人员工号")
    @ExcelProperty("参与人员工号")
    private String jobNo;

    //    (value = "参与者")
    @ExcelProperty("参与人员姓名")
    private String participantName;

    //(value = "证书")
    @ExcelProperty("获得证书名称")
    private String certificate;

    //(value = "获得证书时间")
    @ExcelProperty("证书获得时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" , timezone = "GMT+8")
    private Date beRewardedTime;

    //(value = "录入编号")
    private Long enterId;

    private String others;



}
