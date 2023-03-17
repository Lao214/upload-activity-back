package com.example.activityUP.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.activityUP.entity.SaUser;
import com.example.activityUP.entity.SysEnterActivity;
import com.example.activityUP.entity.SysProject;
import com.example.activityUP.entity.Vo.FormQuery;
import com.example.activityUP.service.SysProjectService;
import com.example.activityUP.utils.Result;
import com.example.activityUP.utils.SaPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 培训项目表 前端控制器
 * </p>
 *
 * @author 劳威锟
 * @since 2023-03-16
 */
@RestController
@RequestMapping("/AU/sysProject")
public class SysProjectController {

    @Autowired
    private SysProjectService sysProjectService;

    @PostMapping("createProject")
    public Result createProject(@RequestBody SysProject projectForm, @RequestHeader("satoken") String satoken){
        // 校验登录
        Result result = SaPermission.checkSaPermission(satoken);
        if(result.getCode() != 200) {
            return result;
        }
        // 添加录入人员ID 从satoken中取出
        Object loginIdByToken = StpUtil.getLoginIdByToken(satoken);
        projectForm.setCreateUserId(Long.parseLong(loginIdByToken.toString()));
        // 添加 录入时间
        projectForm.setCreateTime(new Date());
        projectForm.setUpdateTime(new Date());
        boolean save = sysProjectService.save(projectForm);
        if (save) {
            return result.success().data("data",projectForm);
        } else {
            return result.success().msg("添加失败");
        }
    }


    @PostMapping("getFormDataListPage/{current}/{limit}")
    public Result getFormDataListPage(@PathVariable long current, @PathVariable long limit, @RequestBody(required = false) FormQuery formQuery, @RequestHeader("satoken") String satoken) {
        // 校验登录
        Result result = SaPermission.checkSaPermission(satoken);
        /**创建page对象**/
        Page<SysProject> ceePage = new Page<>(current,limit);
        /**构建条件**/
        QueryWrapper<SysProject> queryWrapper =new QueryWrapper<>();
        //排序 根据创建时间降序
        queryWrapper.orderByDesc("create_time");
        sysProjectService.page(ceePage,queryWrapper);
        /**total 为所有记录**/
        long total = ceePage.getTotal();
        List<SysProject> list= ceePage.getRecords();
        return result.data("rows",list).data("total",total);
    }

}

