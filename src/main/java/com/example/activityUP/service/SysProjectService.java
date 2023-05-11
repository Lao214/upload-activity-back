package com.example.activityUP.service;

import com.example.activityUP.entity.DTO.AllProjectDTO;
import com.example.activityUP.entity.SysProject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.activityUP.entity.Vo.FormQuery;

import java.util.List;

/**
 * <p>
 * 培训项目表 服务类
 * </p>
 *
 * @author 劳威锟
 * @since 2023-03-16
 */
public interface SysProjectService extends IService<SysProject> {

    List<AllProjectDTO> findAllProjects(AllProjectDTO formQuery);

    List<AllProjectDTO> findAllUnit();

    List<AllProjectDTO> findAllDepartment();
}
