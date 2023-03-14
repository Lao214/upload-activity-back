package com.example.activityUP.controller;


import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.activityUP.entity.SaUser;
import com.example.activityUP.entity.Vo.FormQuery;
import com.example.activityUP.entity.Vo.LoginForm;
import com.example.activityUP.service.SaUserService;
import com.example.activityUP.utils.Result;
import com.example.activityUP.utils.SaPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 劳威锟
 * @since 2023-03-13
 */
@RestController
@RequestMapping("/AU/saUser")
public class SaUserController {

    @Autowired
    private  SaUserService saUserService;

    @PostMapping("doLogin")
    public Result doLogin(@RequestBody LoginForm loginForm) {
        // 第1步，先登录上
        QueryWrapper<SaUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",loginForm.getUsername());
        queryWrapper.eq("password",SaSecureUtil.md5(loginForm.getPassword()));
        SaUser one = saUserService.getOne(queryWrapper);
        if(one != null) {
            StpUtil.login(one.getId());
            List<String> permissionList = StpUtil.getPermissionList(one.getId());
            // 第2步，获取 Token  相关参数
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            // 第3步，返回给前端
            return Result.success().data("tokenInfo",tokenInfo).data("permissionList",permissionList);
        }else  {
            // 用户不存在
            return Result.error().code(500).msg("用户名或密码不存在");
        }
    }

    @PostMapping("addUser")
    public Result addUser(@RequestBody SaUser userForm, @RequestHeader("satoken") String satoken){
        // 校验登录并鉴权
        Result result = SaPermission.checkSaPermission(satoken, "user.add");
        userForm.setPassword(SaSecureUtil.md5(userForm.getPassword()));
        userForm.setCreateTime(new Date());
        userForm.setUpdateTime(new Date());
        boolean save = saUserService.save(userForm);
        if (save) {
            return result.success().data("data",userForm);
        } else {
            return result.success().msg("插入失败");
        }
    }

    @PostMapping("getFormDataListPage/{current}/{limit}")
    public Result getFormDataListPage(@PathVariable long current, @PathVariable long limit, @RequestBody(required = false) FormQuery formQuery,@RequestHeader("satoken") String satoken) {
        // 校验登录并鉴权
        Result result = SaPermission.checkSaPermission(satoken, "user.list");
        /**创建page对象**/
        Page<SaUser> ceePage = new Page<>(current,limit);
        /**构建条件**/
        QueryWrapper<SaUser> queryWrapper =new QueryWrapper<>();
        //排序 根据创建时间降序
        queryWrapper.orderByDesc("create_time");
        saUserService.page(ceePage,queryWrapper);
        /**total 为所有记录**/
        long total = ceePage.getTotal();
        List<SaUser> list= ceePage.getRecords();
        return result.data("rows",list).data("total",total);
    }

}

