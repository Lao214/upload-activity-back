package com.example.activityUP.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.activityUP.entity.SaUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 劳威锟
 * @since 2023-03-13
 */
public interface SaUserMapper extends BaseMapper<SaUser> {

    boolean insertBatchOrUpdate(@Param("dataList")List<SaUser> cachedDataList);
}
