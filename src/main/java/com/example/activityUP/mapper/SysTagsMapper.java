package com.example.activityUP.mapper;

import com.example.activityUP.entity.SysTags;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 劳威锟
 * @since 2023-04-23
 */
public interface SysTagsMapper extends BaseMapper<SysTags> {

    boolean insertBatchOrUpdate(@Param("dataList")List<SysTags> dataList);
}
