package com.example.activityUP.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.activityUP.entity.SaMenu;
import com.example.activityUP.entity.SaRoleMenu;
import com.example.activityUP.entity.Vo.AssginMenuVo;
import com.example.activityUP.entity.Vo.SelectedAssign;
import com.example.activityUP.mapper.SaMenuMapper;
import com.example.activityUP.mapper.SaRoleMenuMapper;
import com.example.activityUP.service.SaMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.activityUP.service.SaRoleService;
import com.example.activityUP.utils.MenuHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 劳威锟
 * @since 2023-03-14
 */
@Service
public class SaMenuServiceImpl extends ServiceImpl<SaMenuMapper, SaMenu> implements SaMenuService {


    @Autowired
    private SaRoleMenuMapper saRoleMenuMapper;

    @Override
    public List<SaMenu> findNodes() {
        //获取所有菜单
        List<SaMenu> sysMenuList = baseMapper.findAllMenu();
        //所有菜单数据转换要求数据格式
        List<SaMenu> resultList = MenuHelper.bulidTree(sysMenuList);
        return resultList;
    }



    @Override
    public void doAssign(AssginMenuVo assginMenuVo) {
        //根据角色id删除菜单权限
        QueryWrapper<SaRoleMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id",assginMenuVo.getRoleId());
        saRoleMenuMapper.delete(wrapper);

        //遍历菜单id列表，一个一个进行添加
        List<Long> menuIdList = assginMenuVo.getMenuIdList();
        for (Long menuId:menuIdList) {
            SaRoleMenu sysRoleMenu = new SaRoleMenu();
            sysRoleMenu.setMenuId(menuId);
            sysRoleMenu.setRoleId(assginMenuVo.getRoleId());
            saRoleMenuMapper.insert(sysRoleMenu);
        }
    }

    @Override
    public SelectedAssign findMenuByRoleId(Long roleId) {
        //获取所有菜单 status=1
//        QueryWrapper<SaMenu> wrapperMenu = new QueryWrapper<>();
//        wrapperMenu.eq("status",1);
        List<SaMenu> menuList = baseMapper.findAllMenu();
        List<Long> selectedList = new ArrayList<>();

        //根据角色id查询 角色分配过的菜单列表
        QueryWrapper<SaRoleMenu> wrapperRoleMenu = new QueryWrapper<>();
        wrapperRoleMenu.eq("role_id",roleId);
        List<SaRoleMenu> roleMenus = saRoleMenuMapper.selectList(wrapperRoleMenu);

        //从第二步查询列表中，获取角色分配所有菜单id
        List<Long> roleMenuIds = new ArrayList<>();
        for (SaRoleMenu sysRoleMenu:roleMenus) {
            Long menuId = sysRoleMenu.getMenuId();
            roleMenuIds.add(menuId);
        }

        //数据处理：isSelect 如果菜单选中 true，否则false
        // 拿着分配菜单id 和 所有菜单比对，有相同的，让isSelect值true
        for (SaMenu sysMenu:menuList) {
            if(roleMenuIds.contains(sysMenu.getId())) {
                sysMenu.setSelect(true);
                selectedList.add(sysMenu.getId());
            } else {
                sysMenu.setSelect(false);
            }
        }

        //转换成树形结构为了最终显示 MenuHelper方法实现
        SelectedAssign selectedAssign =new SelectedAssign();
        List<SaMenu> sysMenus = MenuHelper.bulidTree(menuList);
        selectedAssign.setMenuList(sysMenus);
        selectedAssign.setSelectedList(selectedList);
        return selectedAssign;
    }

    @Override
    public List<SaMenu> getUserMenuList(Long id) {
        return null;
    }

    @Override
    public List<String> getUserPerList(Object id) {
        List<String> perListUserId = baseMapper.findPerListUserId(id);
        return perListUserId;
    }
}
