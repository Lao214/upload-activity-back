package com.example.activityUP.service.impl;

import com.example.activityUP.entity.SysActivity;
import com.example.activityUP.entity.SysTags;
import com.example.activityUP.mapper.SysTagsMapper;
import com.example.activityUP.service.SysTagsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 劳威锟
 * @since 2023-04-23
 */
@Service
public class SysTagsServiceImpl extends ServiceImpl<SysTagsMapper, SysTags> implements SysTagsService {

    @Override
    public boolean insertBatchOrUpdate(List<SysTags> dataList) {
        return baseMapper.insertBatchOrUpdate(dataList);
    }
}
