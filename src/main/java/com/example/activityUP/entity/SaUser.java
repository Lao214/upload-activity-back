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
 * @since 2023-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SaUser implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;

    //(value = "昵称")
    private String nickname;

    //(value = "邮箱")
    private String email;

    //(value = "手机号")
    private String phone;

    private String sign;

    private String unit;

    private String realName;

    //(value = "区域")
    private String area;

    private String grade;

    private String factory;

    private String department;

    private String sex;

    //(value = "创建时间")
    private Date createTime;
    //(value = "创建时间")
    private Date updateTime;

    //(value = "头像")
    private String avatar;

    //分机电话
    private String telephone;

    //(value = "来源")
    private String source;


}
