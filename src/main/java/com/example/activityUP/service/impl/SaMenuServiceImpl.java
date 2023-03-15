package com.example.activityUP.service.impl;

import com.example.activityUP.entity.SaMenu;
import com.example.activityUP.mapper.SaMenuMapper;
import com.example.activityUP.service.SaMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.activityUP.service.SaRoleService;
import com.example.activityUP.utils.MenuHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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



    @Override
    public List<SaMenu> findNodes() {
        //获取所有菜单
        List<SaMenu> sysMenuList = baseMapper.selectList(null);
        //所有菜单数据转换要求数据格式
        List<SaMenu> resultList = MenuHelper.bulidTree(sysMenuList);
        return resultList;
    }
}
