package com.example.activityUP.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

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
//(value="SaRole对象", description="")
public class SaRole implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //(value = "角色名称")
    private String roleName;

    //(value = "角色描述")
    private String description;

    //(value = "状态")
    private Integer status;

    //(value = "角色值")
    private String roleCode;

    private Date createTime;

    @TableField(exist = false)
    private Long value;

    @TableField(exist = false)
    private String label;

}
