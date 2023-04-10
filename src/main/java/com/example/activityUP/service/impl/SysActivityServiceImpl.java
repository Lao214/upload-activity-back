package com.example.activityUP.service.impl;

import com.example.activityUP.entity.SysActivity;
import com.example.activityUP.entity.Vo.ActivityVo;
import com.example.activityUP.mapper.SysActivityMapper;
import com.example.activityUP.service.SysActivityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 活动资料 服务实现类
 * </p>
 *
 * @author 劳威锟
 * @since 2023-03-15
 */
@Service
public class SysActivityServiceImpl extends ServiceImpl<SysActivityMapper, SysActivity> implements SysActivityService {

    @Override
    public List<ActivityVo> getProjectActivityList(long offset, long limit, Long id) {
        return baseMapper.getProjectActivityList(offset,limit,id);
    }

    @Override
    public Long getTotalActivityCount(Long projectId) {
        return baseMapper.getTotalActivityCount(projectId);
    }

    @Override
    public void deleteByEnterId(Long enterId) {
        baseMapper.deleteByEnterId(enterId);
    }

    @Override
    public void insertBatchOrUpdate(List<SysActivity> dataList) {
        baseMapper.insertBatchOrUpdate(dataList);
    }
}
