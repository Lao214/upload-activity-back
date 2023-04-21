package com.example.activityUP.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.activityUP.entity.SaUser;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 劳威锟
 * @since 2023-03-13
 */
public interface SaUserService extends IService<SaUser> {

    /** 批量导入 插入或忽略插入 **/
    boolean insertBatchOrUpdate(List<SaUser> cachedDataList);
}
