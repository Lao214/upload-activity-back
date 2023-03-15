package com.example.activityUP.mapper;

import com.example.activityUP.entity.SaUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 劳威锟
 * @since 2023-03-15
 */
public interface SaUserRoleMapper extends BaseMapper<SaUserRole> {

    boolean removeByUserId(@Param("userId")Long id);
}
