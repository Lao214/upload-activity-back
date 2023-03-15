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
public class SaUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //(value = "用户id")
    private Long userId;

    //(value = "角色id")
    private Long roleId;

    //(value = "状态")
    private Integer status;


}
