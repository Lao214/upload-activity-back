package com.example.activityUP.service;

import com.example.activityUP.entity.SaMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.activityUP.entity.Vo.AssginMenuVo;
import com.example.activityUP.entity.Vo.SelectedAssign;

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

    //给角色分配菜单权限
    void doAssign(AssginMenuVo assginMenuVo);

    //根据角色分配菜单
    SelectedAssign findMenuByRoleId(Long roleId);

    List<SaMenu> getUserMenuList(Long id);

    List<String> getUserPerList(Object id);

}
