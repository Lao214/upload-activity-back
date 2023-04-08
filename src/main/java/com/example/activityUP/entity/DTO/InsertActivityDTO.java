package com.example.activityUP.entity.DTO;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author peterlin
 * @version 1.0
 * @description: TODO
 * @date 2023/3/17 4:39 PM
 */
@Data
public class InsertActivityDTO {

    private static final long serialVersionUID = 1L;

    /** 录入项目ID **/
    private Long enterProjectId;

    /** projectName 项目名称 **/
    private String name;

    /** credit 学分 **/
    private Double credit;

    /** hours 培训时数 **/
    private Double hours;

    /** enterUserId 录入人员ID **/
    private Long enterUserId;

    /** enterUserName 录入人员姓名 **/
    private String enterUserName;

    /** enterJobNo 录入人员工号 **/
    private String enterJobNo;

    /**  起止时间 **/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" , timezone = "GMT+8")
    public Date beginTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" , timezone = "GMT+8")
    public Date endTime;

    /** 参与人员工号 **/
    private String jobNo;

    /** 参与人员姓名 **/
    private String participantName;

    /** 获得证书名称 **/
    private String certificate;

    /** 获得证书时间 **/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" , timezone = "GMT+8")
    private Date beRewardedTime;
}
