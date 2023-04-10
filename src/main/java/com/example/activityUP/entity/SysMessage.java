package com.example.activityUP.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2023-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
//ApiModel(value="SysMessage对象", description="")
public class SysMessage implements Serializable {

    private static final long serialVersionUID = 1L;

   //value = "id")
   @TableId(value = "id", type = IdType.AUTO)
    private Long id;

   //value = "内容")
    private String message;

   //value = "姓名")
    private String name;

   //value = "工号")
    private String jobNo;

    private Date createTime;


}
