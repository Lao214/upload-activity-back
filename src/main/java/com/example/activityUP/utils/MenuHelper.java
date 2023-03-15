package com.example.activityUP.utils;

import com.example.activityUP.entity.SaMenu;


import java.util.ArrayList;
import java.util.List;

public class MenuHelper {

    //构建树形结构
    public static List<SaMenu> bulidTree(List<SaMenu> sysMenuList) {
        //创建集合封装最终数据
        List<SaMenu> trees = new ArrayList<>();
        //遍历所有菜单集合
        for (SaMenu sysMenu:sysMenuList) {
            //找到递归入口，parentid=0
            if(sysMenu.getParentId().longValue()==0) {
                trees.add(findChildren(sysMenu,sysMenuList));
            }
        }
        return trees;
    }

    //从根节点进行递归查询，查询子节点
    // 判断 id =  parentid是否相同，如果相同是子节点，进行数据封装
    private static SaMenu findChildren(SaMenu sysMenu, List<SaMenu> treeNodes) {
        //数据初始化
        sysMenu.setChildren(new ArrayList<SaMenu>());
        //遍历递归查找
        for (SaMenu it:treeNodes) {
            //获取当前菜单id
//            String id = sysMenu.getId();
//            long cid = Long.parseLong(id);
            //获取所有菜单parentid
//            Long parentId = it.getParentId();
            //比对
            if(sysMenu.getId() == it.getParentId()) {
                if(sysMenu.getChildren()==null) {
                    sysMenu.setChildren(new ArrayList<>());
                }
                sysMenu.getChildren().add(findChildren(it,treeNodes));
            }
        }
        return sysMenu;
    }
}
