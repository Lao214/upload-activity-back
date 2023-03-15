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
 * @since 2023-03-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
//@ApiModel(value="SaRoleMenu对象", description="")
public class SaRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    //(value = "id")
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //(value = "角色id")
    private Long roleId;

    //(value = "权限id")
    private Long menuId;

    //(value = "状态")
    private Integer status;


}
