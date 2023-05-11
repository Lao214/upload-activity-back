package com.example.activityUP.service.impl;

import com.example.activityUP.entity.DTO.AllProjectDTO;
import com.example.activityUP.entity.SysProject;
import com.example.activityUP.entity.Vo.FormQuery;
import com.example.activityUP.mapper.SysProjectMapper;
import com.example.activityUP.service.SysProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 培训项目表 服务实现类
 * </p>
 *
 * @author 劳威锟
 * @since 2023-03-16
 */
@Service
public class SysProjectServiceImpl extends ServiceImpl<SysProjectMapper, SysProject> implements SysProjectService {

    @Override
    public List<AllProjectDTO> findAllProjects( AllProjectDTO formQuery) {
        return baseMapper.findAllProjects(formQuery);
    }

    @Override
    public List<AllProjectDTO> findAllUnit() {
        return baseMapper.findAllUnit();
    }

    @Override
    public List<AllProjectDTO> findAllDepartment() {
        return baseMapper.findAllDepartment();
    }
}
