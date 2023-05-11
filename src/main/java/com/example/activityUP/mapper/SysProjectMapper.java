package com.example.activityUP.mapper;

import com.example.activityUP.entity.DTO.AllProjectDTO;
import com.example.activityUP.entity.SysProject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.activityUP.entity.Vo.FormQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 培训项目表 Mapper 接口
 * </p>
 *
 * @author 劳威锟
 * @since 2023-03-16
 */
public interface SysProjectMapper extends BaseMapper<SysProject> {

    List<AllProjectDTO> findAllUnit();

    List<AllProjectDTO> findAllProjects(@Param("condition") AllProjectDTO condition);

    List<AllProjectDTO> findAllDepartment();
}
