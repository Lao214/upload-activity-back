package com.example.activityUP.service;

import com.example.activityUP.entity.SaMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 劳威锟
 * @since 2023-03-14
 */
public interface SaMenuService extends IService<SaMenu> {

    //菜单列表（树形）
     List<SaMenu> findNodes();

}
