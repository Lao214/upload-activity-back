package com.example.activityUP.entity.Vo;


import lombok.Data;

/**
 * @author 劳威锟
 * @version 1.0
 * @description: TODO
 * @date 2022/10/28 3:07 PM
 */
@Data
public class FormQuery {

    //(value = "问卷名称，模糊查询")
    private String name;

    private String sex;

    //(area) 区域
    private String area;

    //(value = "状态 1已发布 0未发布")
    private Integer status;

    //(value = "类型 1有反馈 2无反溃")
    private Integer type;

    //(value = "查询开始时间",example = "2022-02-27 10:22:12")
    private String begin;

    //(value = "查询结束时间",example = "2022-02-28 10:22:12")
    private String end;
}
