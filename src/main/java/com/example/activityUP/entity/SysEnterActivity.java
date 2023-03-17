package com.example.activityUP.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 录入与审核记录表
 * </p>
 *
 * @author 劳威锟
 * @since 2023-03-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
//@ApiModel(value="SysEnterActivity对象", description="录入与审核记录表")
public class SysEnterActivity implements Serializable {

    private static final long serialVersionUID = 1L;

    //(value = "id（录入编号）")
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //(value = "录入人员ID")
    private Long enterUserId;

    //(value = "录入人员姓名")
    private String enterUserName;

    //(value = "审核人员ID")
    private Long auditUserId;

    //(value = "录入时间")
    private Date enterTime;

    //(value = "审核时间")
    private Date auditTime;

    //(value = "录入时间")
    private Date beginTime;

    //(value = "审核时间")
    private Date endTime;

    //(value = "录入项目名称")
    private String enterName;

    //(value = "审核人员姓名")
    private String auditUserName;

    //(value = "审核人员工号")
    private String enterJobNo;

    //(value = "录入项目ID")
    private Long enterProjectId;

    //(value = "状态（0:未审核，1:已审核）")
    private Integer status;


}
