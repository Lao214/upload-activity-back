package com.example.activityUP.entity.Vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.util.Date;

/**
 * @author peterlin
 * @version 1.0
 * @description: TODO
 * @date 2023/3/18 5:18 PM
 */

@Getter
@Setter
@ExcelIgnoreUnannotated
@ContentRowHeight(21)
@HeadRowHeight(30)
@ColumnWidth(15)
@FieldNameConstants
@EqualsAndHashCode(callSuper = false)
public class ActivityVo {

    private Long id;
    @ExcelProperty("参与人员工号")
    private String jobNo;
    @ExcelProperty("参与人员姓名")
    private String participantName;
    @ExcelProperty("获得证书名称")
    private String certificate;
    @ExcelProperty("录入人员姓名")
    private String enterUserName;
    @ExcelProperty("录入人员工号")
    private String enterJobNo;
    @ExcelProperty("录入时间")
    private Date enterTime;
    @ExcelProperty("获得证书时间")
    private Date beRewardedTime;
    @ExcelProperty("开始时间")
    private Date beginTime;
    @ExcelProperty("结束时间")
    private Date endTime;
    @ExcelProperty("状态")
    private Integer status;
    @ExcelIgnore
    private Long auditUserId;
    @ExcelProperty("审核人员工号")
    private String auditUserName;
    @ExcelProperty("审核时间")
    private Date auditTime;
}
