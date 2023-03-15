package com.example.activityUP.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 劳威锟
 * @since 2023-03-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
//ApiModel(value="SaMenu对象", description="")
public class SaMenu implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //(value = "状态")
    private Integer status;

    //(value = "指向路由")
    private String router;

    //(value = "描述")
    private String description;

    //(value = "权限名")
    private String menuName;

    //(value = "权限值")
    private String menuValue;

    //(value = "类型")
    private Integer type;

    //(value = "级别")
    private Integer level;

    //(value = "父id")
    private Long parentId;

    @TableField(exist = false)
    private List<SaMenu> children;
}
