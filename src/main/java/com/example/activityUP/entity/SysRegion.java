package com.example.activityUP.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2023-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRegion implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "value", type = IdType.AUTO)
    private Long value;

    //(value = "名称")
    private String label;

    //(value = "上级id")
    private Long parentId;

    //(value = "level_id")
    private String levelId;


}
