package com.example.activityUP.entity.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author lao
 * @version 1.0
 * @description: TODO
 * @date 2023/5/5 3:13 PM
 */
@Data
public class AllProjectDTO {

    private static final long serialVersionUID = 1L;

    Integer id;

    Integer projectId;

    String username;

    String realName;

    String department;

    String unit;

    String projectName;

    /** 人数 **/
    Integer numOfProject;

    /** 学时 **/
    Double hours;

    /** 学分 **/
    Double credit;

    /** 开始时间 **/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    Date beginTime;

    /** 结束时间 **/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    Date endTime;

    /** 创建时间 **/
    Date createTime;

    List<AllProjectDTO> children;

    Integer level;

}
