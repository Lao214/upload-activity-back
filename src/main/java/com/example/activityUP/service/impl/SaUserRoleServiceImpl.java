package com.example.activityUP.service.impl;

import com.example.activityUP.entity.SaUserRole;
import com.example.activityUP.mapper.SaUserRoleMapper;
import com.example.activityUP.service.SaUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 劳威锟
 * @since 2023-03-15
 */
@Service
public class SaUserRoleServiceImpl extends ServiceImpl<SaUserRoleMapper, SaUserRole> implements SaUserRoleService {



    @Override
    public boolean removeByUserId(Long id) {
        return baseMapper.removeByUserId(id);
    }
}
