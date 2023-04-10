package com.example.activityUP.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.example.activityUP.entity.SaUser;
import com.example.activityUP.entity.SysEnterActivity;
import com.example.activityUP.entity.SysMessage;
import com.example.activityUP.service.SysMessageService;
import com.example.activityUP.utils.Result;
import com.example.activityUP.utils.SaPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 劳威锟
 * @since 2023-04-10
 */
@RestController
@RequestMapping("/AU/message")
public class SysMessageController {

    @Autowired
    private SysMessageService sysMessageService;

    @PostMapping("addMessage")
    public Result addMessage(@RequestHeader("satoken") String satoken, @RequestBody SysMessage sysMessage) {
        sysMessage.setCreateTime(new Date());
        boolean save = sysMessageService.save(sysMessage);
        if (save) {
            return Result.success().msg("发送成功");
        } else {
            return Result.error().msg("发送失败");
        }
    }
}

