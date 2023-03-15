package com.example.activityUP.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.activityUP.entity.SaRole;
import com.example.activityUP.entity.SaUser;
import com.example.activityUP.entity.SaUserRole;
import com.example.activityUP.entity.Vo.AssginRoleVo;
import com.example.activityUP.entity.Vo.SelectedAssign;
import com.example.activityUP.mapper.SaRoleMapper;
import com.example.activityUP.mapper.SaUserMapper;
import com.example.activityUP.mapper.SaUserRoleMapper;
import com.example.activityUP.service.SaRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 劳威锟
 * @since 2023-03-14
 */
@Service
public class SaRoleServiceImpl extends ServiceImpl<SaRoleMapper, SaRole> implements SaRoleService {

    @Autowired
    private SaUserRoleMapper saUserRoleMapper;

    @Autowired
    private SaUserMapper saUserMapper;

    @Override
    public List<SaRole> options() {
        return baseMapper.options();
    }

    @Override
    public void doAssign(AssginRoleVo assginRoleVo) {
        //根据用户id删除之前分配角色
        QueryWrapper<SaUserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",assginRoleVo.getUserId());
        saUserRoleMapper.delete(wrapper);
        //获取所有角色id，添加角色用户关系表
        //角色id列表
        List<String> roleIdList = assginRoleVo.getRoleIdList();
        List<String> roleNameList = assginRoleVo.getRoleNameList();
        // 在user表中记录角色
        String join = StringUtils.join(roleNameList);
        SaUser saUser = new SaUser();
        saUser.setId(assginRoleVo.getUserId());
        saUser.setGrade(join);
        saUserMapper.updateById(saUser);
        // 循环分配角色
        for (String roleId:roleIdList) {
            SaUserRole userRole = new SaUserRole();
            userRole.setUserId(assginRoleVo.getUserId());
            userRole.setRoleId(Long.parseLong(roleId));
            saUserRoleMapper.insert(userRole);
        }
    }

    @Override
    public Map<String, Object> getRolesByUserId(Long userId) {
        //获取所有角色
        List<SaRole> roles = baseMapper.options();
        //根据用户id查询，已经分配角色
        QueryWrapper<SaUserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        List<SaUserRole> userRolesList = saUserRoleMapper.selectList(wrapper);
        //从userRoles集合获取所有角色id
        List<Long> userRoleIds = new ArrayList<>();
        for (SaUserRole userRole:userRolesList) {
            Long roleId = userRole.getRoleId();
            userRoleIds.add(roleId);
        }
        //封装到map集合
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("allRoles",roles);//所有角色
        returnMap.put("userRoleIds",userRoleIds);//用户分配角色id集合
        return returnMap;
    }
}
