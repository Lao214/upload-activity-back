package com.example.activityUP.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
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
 * @since 2023-04-23
 */
@Getter
@Setter
@ExcelIgnoreUnannotated
@ContentRowHeight(21)
@HeadRowHeight(30)
@ColumnWidth(15)
@FieldNameConstants
@EqualsAndHashCode(callSuper = false)
//@ApiModel(value="SysTags对象", description="")
public class SysTags implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //(value = "关键词中文")
    @ExcelProperty("中文")
    private String keywordCn;

    //(value = "关键词英文")
    @ExcelProperty("英文")
    private String keywordEn;

    //(value = "级别")
    @ExcelProperty("级别")
    private Integer level;

    //(value = "父级id")
    @ExcelProperty("父级ID")
    private Long parentId;

    //(value = "排序")
    private Long sort;

    //(value = "创建时间")
    private Date createTime;


}
