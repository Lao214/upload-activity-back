package com.example.activityUP.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 劳威锟
 * @since 2023-04-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
//@ApiModel(value="SysTags对象", description="")
public class SysTags implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //(value = "关键词中文")
    private String keywordCn;

    //(value = "关键词英文")
    private String keywordEn;

    //(value = "级别")
    private Integer level;

    //(value = "父级id")
    private Long parentId;

    //(value = "排序")
    private Long sort;

    //(value = "创建时间")
    private Date createTime;


}
