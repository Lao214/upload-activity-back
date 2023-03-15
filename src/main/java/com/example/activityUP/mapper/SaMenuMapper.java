package com.example.activityUP.mapper;

import com.example.activityUP.entity.SaMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

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

    List<SaMenu> findMenuListUserId(@Param("userId") Long userId);

    List<String> findPerListUserId(@Param("userId") Object userId);
}
