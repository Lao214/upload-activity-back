package com.example.activityUP.controller;


import cn.dev33.satoken.secure.SaSecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.activityUP.entity.SaRole;
import com.example.activityUP.entity.SaUser;
import com.example.activityUP.entity.Vo.FormQuery;
import com.example.activityUP.service.SaRoleService;
import com.example.activityUP.service.SaUserService;
import com.example.activityUP.utils.Result;
import com.example.activityUP.utils.SaPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 劳威锟
 * @since 2023-03-14
 */
@RestController
@RequestMapping("/AU/saRole")
public class SaRoleController {

    @Autowired
    private SaRoleService saRoleService;

    @PostMapping("addRole")
    public Result addRole(@RequestBody SaRole roleForm, @RequestHeader("satoken") String satoken){
        // 校验登录
        Result result = SaPermission.checkSaPermission(satoken);
        roleForm.setCreateTime(new Date());
        boolean save = saRoleService.save(roleForm);
        if (save) {
            return result.success().data("data",roleForm);
        } else {
            return result.success().msg("添加失败");
        }
    }

    @PostMapping("updateRole")
    public Result updateUser(@RequestBody SaRole updateForm, @RequestHeader("satoken") String satoken){
        // 校验登录
        Result result = SaPermission.checkSaPermission(satoken);
        boolean save = saRoleService.updateById(updateForm);
        if (save) {
            return result.success().data("data",updateForm);
        } else {
            return result.success().msg("修改失败");
        }
    }

    @PostMapping("getFormDataListPage/{current}/{limit}")
    public Result getFormDataListPage(@PathVariable long current, @PathVariable long limit, @RequestBody(required = false) FormQuery formQuery, @RequestHeader("satoken") String satoken) {
        // 校验登录
        Result result = SaPermission.checkSaPermission(satoken);
        /**创建page对象**/
        Page<SaRole> ceePage = new Page<>(current,limit);
        /**构建条件**/
        QueryWrapper<SaRole> queryWrapper =new QueryWrapper<>();
        //排序 根据创建时间降序
        queryWrapper.orderByDesc("create_time");
        saRoleService.page(ceePage,queryWrapper);
        /**total 为所有记录**/
        long total = ceePage.getTotal();
        List<SaRole> list= ceePage.getRecords();
        return result.data("rows",list).data("total",total);
    }
}

