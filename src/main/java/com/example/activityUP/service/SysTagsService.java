package com.example.activityUP.service;

import com.example.activityUP.entity.SysActivity;
import com.example.activityUP.entity.SysTags;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 劳威锟
 * @since 2023-04-23
 */
public interface SysTagsService extends IService<SysTags> {

    /** 批量导入 插入或忽略插入 **/
    boolean insertBatchOrUpdate(List<SysTags> dataList);

}
