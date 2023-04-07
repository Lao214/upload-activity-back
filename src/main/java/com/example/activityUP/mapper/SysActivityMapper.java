package com.example.activityUP.mapper;

import com.example.activityUP.entity.SysActivity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.activityUP.entity.Vo.ActivityVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 活动资料 Mapper 接口
 * </p>
 *
 * @author 劳威锟
 * @since 2023-03-15
 */
public interface SysActivityMapper extends BaseMapper<SysActivity> {

    List<ActivityVo> getProjectActivityList(@Param("offset") long offset, @Param("limit")long limit, @Param("id")Long id);

    Long getTotalActivityCount(Long projectId);

    void deleteByEnterId(Long id);
}
