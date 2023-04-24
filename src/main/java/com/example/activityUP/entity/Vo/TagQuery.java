package com.example.activityUP.entity.Vo;


import lombok.Data;

/**
 * @author 劳威锟
 * @version 1.0
 * @description: TODO
 * @date 2022/10/28 3:07 PM
 */
@Data
public class TagQuery {

    private Long id;

    private String keywordCn;

    private String keywordEn;

    private String level;

    private Integer sort;
}
