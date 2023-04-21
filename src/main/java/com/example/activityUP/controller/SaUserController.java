package com.example.activityUP.controller;


import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.activityUP.entity.DTO.UploadActivityDTO;
import com.example.activityUP.entity.SaUser;
import com.example.activityUP.entity.SaUserRole;
import com.example.activityUP.entity.SysActivity;
import com.example.activityUP.entity.SysEnterActivity;
import com.example.activityUP.entity.Vo.FormQuery;
import com.example.activityUP.entity.Vo.LoginForm;
import com.example.activityUP.service.SaUserRoleService;
import com.example.activityUP.service.SaUserService;
import com.example.activityUP.utils.Result;
import com.example.activityUP.utils.SaPermission;
import com.example.activityUP.utils.UploadDataListener;
import com.example.activityUP.utils.UploadUserListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @Autowired
    private SaUserRoleService saUserRoleService;

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

    @GetMapping("getUserInfo")
    public Result getUserInfo(@RequestHeader("satoken") String satoken) {
        // 校验登录
        Result result = SaPermission.checkSaPermission(satoken);
        if(result.getCode() != 200) {
            return result;
        }
        Object loginIdByToken = StpUtil.getLoginIdByToken(satoken);
        SaUser one = saUserService.getById(loginIdByToken.toString());
        if(one != null) {
            return Result.success().data("userInfo",one);
        }else  {
            // 用户不存在
            return Result.error().code(500).msg("用户名或密码不存在");
        }
    }

    @PostMapping("addUser")
    public Result addUser(@RequestBody SaUser userForm, @RequestHeader("satoken") String satoken){
        // 校验登录
        Result result = SaPermission.checkSaPermission(satoken);
        userForm.setPassword(SaSecureUtil.md5(userForm.getPassword()));
        userForm.setCreateTime(new Date());
        userForm.setUpdateTime(new Date());
        /** 默认普通用户 **/
        userForm.setGrade("[普通用户]");
        boolean save = saUserService.save(userForm);
        SaUserRole saUserRole = new SaUserRole();
        saUserRole.setUserId(userForm.getId());
        saUserRole.setStatus(1);
        saUserRole.setRoleId(2l);
        saUserRoleService.save(saUserRole);
        if (save) {
            return result.success().data("data",userForm);
        } else {
            return result.success().msg("添加失败");
        }
    }

    @PostMapping("updateUser")
    public Result updateUser(@RequestBody SaUser updateForm, @RequestHeader("satoken") String satoken){
        // 校验登录
        Result result = SaPermission.checkSaPermission(satoken);
//        updateForm.setPassword(SaSecureUtil.md5(updateForm.getPassword()));
        updateForm.setUpdateTime(new Date());
        boolean save = saUserService.updateById(updateForm);
        if (save) {
            return result.data("data",updateForm);
        } else {
            return result.msg("修改失败");
        }
    }

    @PostMapping("updatePassword")
    public Result updatePassword(@RequestBody SaUser form, @RequestHeader("satoken") String satoken){
        // 校验登录
        Result result = SaPermission.checkSaPermission(satoken);
        form.setUpdateTime(new Date());
        // 校验原密码
        QueryWrapper<SaUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",form.getUsername());
        queryWrapper.eq("password",SaSecureUtil.md5(form.getPassword()));
        SaUser one = saUserService.getOne(queryWrapper);
        if(one != null) {
            SaUser saUser = new SaUser();
            saUser.setId(form.getId());
            saUser.setPassword(SaSecureUtil.md5(form.getNewPwd()));
            boolean update = saUserService.updateById(saUser);
            if(update) {
                return Result.success().msg("修改成功");
            } else  {
                return Result.error().code(500).msg("修改失败");
            }
        }else  {
            // 用户不存在
            return Result.error().code(500).msg("原密码输入错误");
        }

    }

    @PostMapping("getFormDataListPage/{current}/{limit}")
    public Result getFormDataListPage(@PathVariable long current, @PathVariable long limit, @RequestBody(required = false) FormQuery formQuery,@RequestHeader("satoken") String satoken) {
        // 校验登录
        Result result = SaPermission.checkSaPermission(satoken);
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

    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        boolean is_Success = saUserService.removeById(id);
        if(is_Success) {
            saUserRoleService.removeByUserId(id);
            return Result.success();
        } else {
            return Result.error();
        }
    }

    /**
     * 文件上传 批量插入 活动资料
     * <p>1. 创建excel对应的实体对象 参照{@link }
     * <p>2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link UploadDataListener}
     * <p>3. 直接读即可
     */
    @PostMapping("upload")
    @ResponseBody
    public Result upload(@RequestPart("file") MultipartFile file, @RequestHeader("satoken") String satoken) throws IOException {
        // 校验登录
        Result result = SaPermission.checkSaPermission(satoken);
        EasyExcel.read(file.getInputStream(), SaUser.class, new UploadUserListener(saUserService,saUserRoleService)).sheet().doRead();
        return result;
    }

}

