package com.example.activityUP.service;

import com.example.activityUP.entity.SysActivity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.activityUP.entity.Vo.ActivityVo;

import java.util.List;

/**
 * <p>
 * 活动资料 服务类
 * </p>
 * @author 劳威锟
 * @since 2023-03-15
 */
public interface SysActivityService extends IService<SysActivity> {


    List<ActivityVo>  getProjectActivityList(long offset, long limit, Long projectId);

    Long getTotalActivityCount(Long projectId);

    void deleteByEnterId(Long enterId);
}
