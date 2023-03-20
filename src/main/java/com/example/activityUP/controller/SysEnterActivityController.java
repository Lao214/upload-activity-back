package com.example.activityUP.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.activityUP.entity.SaUser;
import com.example.activityUP.entity.SysEnterActivity;
import com.example.activityUP.entity.SysProject;
import com.example.activityUP.entity.Vo.FormQuery;
import com.example.activityUP.service.SaUserService;
import com.example.activityUP.service.SysEnterActivityService;
import com.example.activityUP.service.SysProjectService;
import com.example.activityUP.utils.Result;
import com.example.activityUP.utils.SaPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 录入与审核记录表 前端控制器
 * </p>
 *
 * @author 劳威锟
 * @since 2023-03-16
 */
@RestController
@RequestMapping("/AU/sysEnterActivity")
public class SysEnterActivityController {

    @Autowired
    private SysEnterActivityService sysEnterActivityService;

    @Autowired
    private SysProjectService sysProjectService;

    @Autowired
    private SaUserService saUserService;

    @PostMapping("auditEnter/{id}/{auditStatus}")
    public Result auditEnter(@PathVariable Long id,@PathVariable Integer auditStatus, @RequestHeader("satoken") String satoken) {
        // 校验登录
        Result result = SaPermission.checkSaPermission(satoken);
        if(result.getCode() != 200) {
            return result;
        }
        Object loginIdByToken = StpUtil.getLoginIdByToken(satoken);
        SaUser one = saUserService.getById(loginIdByToken.toString());
        SysEnterActivity sysEnterActivity = new SysEnterActivity();
        sysEnterActivity.setId(id);
        sysEnterActivity.setStatus(auditStatus);
        sysEnterActivity.setAuditTime(new Date());
        sysEnterActivity.setAuditUserId(one.getId());
        sysEnterActivity.setAuditUserName(one.getUsername());
        sysEnterActivityService.updateById(sysEnterActivity);
        return result;
    }

    @PostMapping("getFormDataListPage/{current}/{limit}")
    public Result getFormDataListPage(@PathVariable long current, @PathVariable long limit, @RequestBody(required = false) FormQuery formQuery, @RequestHeader("satoken") String satoken) {
        // 校验登录
        Result result = SaPermission.checkSaPermission(satoken);
        if(result.getCode() != 200) {
            return result;
        }
        /**创建page对象**/
        Page<SysEnterActivity> ceePage = new Page<>(current,limit);
        /**构建条件**/
        QueryWrapper<SysEnterActivity> queryWrapper =new QueryWrapper<>();
        // 查询 状态
        queryWrapper.eq("status",formQuery.getStatus());
        //排序 根据创建时间降序
        queryWrapper.orderByDesc("enter_time");
        sysEnterActivityService.page(ceePage,queryWrapper);
        /**total 为所有记录**/
        long total = ceePage.getTotal();
        List<SysEnterActivity> list= ceePage.getRecords();
        return result.data("rows",list).data("total",total);
    }
}

