package com.example.activityUP.mapper;

import com.example.activityUP.entity.SaMenu;
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
public interface SaMenuMapper extends BaseMapper<SaMenu> {

    List<SaMenu> findAllMenu();

}
