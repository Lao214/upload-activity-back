package com.example.activityUP.entity.Vo;

import lombok.Data;

import java.util.Date;

/**
 * @author peterlin
 * @version 1.0
 * @description: TODO
 * @date 2023/3/18 5:18 PM
 */
@Data
public class ActivityVo {

    private Long id;

    private String jobNo;

    private String participantName;

    private String certificate;

    private String enterUserName;

    private String enterJobNo;

    private Date beRewardedTime;

    private Date enterTime;

    private Date beginTime;

    private Date endTime;

    private Integer status;

    private Long auditUserId;

    private String auditUserName;

    private Date auditTime;
}
