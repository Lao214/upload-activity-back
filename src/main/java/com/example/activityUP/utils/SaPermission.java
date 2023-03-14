package com.example.activityUP.utils;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;

/**
 * @author peterlin
 * @version 1.0
 * @description: TODO
 * @date 2023/3/14 11:34 AM
 */
public class SaPermission {

    /**
     * @description: 校验登录和权限
     * @param:
     * @return:
     * @author 劳威锟
     * @date: 2023/3/14 11:56 AM
     */
    public static Result checkSaPermission(String satoken,String permission){
        if(!StrUtil.isEmpty(satoken)) {
            /** 在当前会话中写入 Token 值 **/
            StpUtil.setTokenValue(satoken);
            /** 获取当前登录者的 token 剩余有效时间 (单位: 秒) **/
            long tokenTimeout = StpUtil.getTokenTimeout();
            if(tokenTimeout < 0) {
                return Result.error().msg("您的登录令牌已经过期，请重新登录");
            } else {
                /** 获取登录ID **/
                Object loginIdByToken = StpUtil.getLoginIdByToken(satoken);
                if(loginIdByToken != null) {
                    // 这个hasPermission方法会调用StpUtil.getPermissionList(loginId)
                    boolean flag = StpUtil.hasPermission(loginIdByToken,permission);
                    if(!flag) {
                        return Result.success().code(401).msg("权限不足请联系管理员");
                    }
                } else {
                    return Result.error().msg("找不到您的账号信息，请重新登录");
                }
            }
            return Result.success();
        } else {
            return Result.error().msg("找不到您的登录信息，请重新登录");
        }
    }

    public static Result checkSaPermission(String satoken){
        if(!StrUtil.isEmpty(satoken)) {
            StpUtil.setTokenValue(satoken);   // 在当前会话中写入 Token 值。
            long tokenTimeout = StpUtil.getTokenTimeout();// 获取当前登录者的 token 剩余有效时间 (单位: 秒)
            if(tokenTimeout < 0) {
                return Result.error().msg("您的登录令牌已经过期，请重新登录");
            }
            else {
                /** 获取登录ID **/
                Object loginIdByToken = StpUtil.getLoginIdByToken(satoken);
                if(loginIdByToken == null) {
                    return Result.error().msg("找不到您的账号信息，请重新登录");
                }
            }
            return Result.success();
        } else {
            return Result.error().msg("找不到您的登录信息，请重新登录");
        }
    }
}
