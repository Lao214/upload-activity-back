package com.example.activityUP.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

/**
 * <p>
 * 
 * </p>
 *
 * @author 劳威锟
 * @since 2023-03-13
 */
@Getter
@Setter
@ExcelIgnoreUnannotated
@ContentRowHeight(21)
@HeadRowHeight(30)
@ColumnWidth(15)
@FieldNameConstants
@EqualsAndHashCode(callSuper = false)
public class SaUser implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ExcelProperty("工号")
    private String username;

    private String password;

    //(value = "昵称")
    private String nickname;

    //(value = "邮箱")
    private String email;

    //(value = "手机号")
    private String phone;

    private String sign;

    @ExcelProperty("事业群")
    private String unit;

    @ExcelProperty("姓名")
    private String realName;

    //(value = "区域")
    private String area;

    private String grade;

    private String factory;

    @ExcelProperty("部门")
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

    @TableField(exist = false)
    private String newPwd;

    @ExcelProperty("处级单位")
    private String businessUnit;
}
