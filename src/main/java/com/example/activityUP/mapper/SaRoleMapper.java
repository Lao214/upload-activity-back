package com.example.activityUP.mapper;

import com.example.activityUP.entity.SaRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 劳威锟
 * @since 2023-03-14
 */
public interface SaRoleMapper extends BaseMapper<SaRole> {
    List<SaRole> options();
}
