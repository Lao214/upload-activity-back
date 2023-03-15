package com.example.activityUP.service;

import com.example.activityUP.entity.SaRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.activityUP.entity.Vo.AssginRoleVo;
import com.example.activityUP.entity.Vo.SelectedAssign;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 劳威锟
 * @since 2023-03-14
 */
public interface SaRoleService extends IService<SaRole> {


    List<SaRole> options();

    void doAssign(AssginRoleVo assginRoleVo);

    Map<String, Object> getRolesByUserId(Long userId);
}
