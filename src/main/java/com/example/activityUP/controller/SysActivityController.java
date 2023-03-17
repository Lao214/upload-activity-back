package com.example.activityUP.controller;


import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.excel.EasyExcel;
import com.example.activityUP.entity.DTO.UploadActivityDTO;
import com.example.activityUP.entity.SaUser;
import com.example.activityUP.entity.SaUserRole;
import com.example.activityUP.entity.SysActivity;
import com.example.activityUP.entity.SysEnterActivity;
import com.example.activityUP.service.SysActivityService;
import com.example.activityUP.service.SysEnterActivityService;
import com.example.activityUP.utils.Result;
import com.example.activityUP.utils.SaPermission;
import com.example.activityUP.utils.UploadDataListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

/**
 * <p>
 * 活动资料 前端控制器
 * </p>
 *
 * @author 劳威锟
 * @since 2023-03-15
 */
@RestController
@RequestMapping("/AU/sysActivity")
public class SysActivityController {

    @Autowired
    private SysActivityService sysActivityService;

    @Autowired
    private SysEnterActivityService sysEnterActivityService;

    /**
     * 文件上传
     * <p>1. 创建excel对应的实体对象 参照{@link }
     * <p>2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link UploadDataListener}
     * <p>3. 直接读即可
     */
    @PostMapping("upload")
    @ResponseBody
    public Result upload(@RequestPart("file")MultipartFile file,@RequestHeader("satoken") String satoken,@RequestPart("uploadActivityDTO")UploadActivityDTO uploadActivityDTO) throws IOException {
        // 校验登录
        Result result = SaPermission.checkSaPermission(satoken);
        SysEnterActivity enterActivity = new SysEnterActivity();
        // 录入项目ID
        enterActivity.setEnterProjectId(uploadActivityDTO.getEnterProjectId());
        // 录入项目名称
        enterActivity.setEnterName(uploadActivityDTO.getName());
        // 录入人员id
        enterActivity.setEnterUserId(uploadActivityDTO.getEnterUserId());
        // 录入人员姓名
        enterActivity.setEnterUserName(uploadActivityDTO.getEnterUserName());
        // 录入人员姓名
        enterActivity.setEnterJobNo(uploadActivityDTO.getEnterJobNo());
        // 默认状态未审核
        enterActivity.setStatus(0);
        enterActivity.setEnterTime(new Date());
        // 起始时间
        enterActivity.setBeginTime(uploadActivityDTO.getBeginTime());
        enterActivity.setEndTime(uploadActivityDTO.getEndTime());
        boolean save = sysEnterActivityService.save(enterActivity);
        if(save) {
            EasyExcel.read(file.getInputStream(), SysActivity.class, new UploadDataListener(sysActivityService,enterActivity.getId())).sheet().doRead();
        } else {
            return Result.error().msg("导入失败");
        }
        return result;
    }


    @PostMapping("createEnterProject")
    public Result addUser(@RequestBody SysEnterActivity enterForm, @RequestHeader("satoken") String satoken){
        // 校验登录
        Result result = SaPermission.checkSaPermission(satoken);
        if(result.getCode() != 200) {
            return result;
        }
        // 添加录入人员ID 从satoken中取出
        Object loginIdByToken = StpUtil.getLoginIdByToken(satoken);
        enterForm.setEnterUserId((Long) loginIdByToken);
        // 添加 录入时间
        enterForm.setEnterTime(new Date());
        boolean save = sysEnterActivityService.save(enterForm);
        if (save) {
            return result.data("data",enterForm);
        } else {
            return result.msg("添加失败");
        }
    }


}

