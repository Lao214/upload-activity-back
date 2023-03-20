package com.example.activityUP.controller;


import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.activityUP.entity.*;
import com.example.activityUP.entity.DTO.InsertActivityDTO;
import com.example.activityUP.entity.DTO.UploadActivityDTO;
import com.example.activityUP.entity.Vo.ActivityVo;
import com.example.activityUP.entity.Vo.FormQuery;
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
import java.util.List;

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
     * 文件上传 批量插入 活动资料
     * <p>1. 创建excel对应的实体对象 参照{@link }
     * <p>2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link UploadDataListener}
     * <p>3. 直接读即可
     */
    @PostMapping("upload")
    @ResponseBody
    public Result upload(@RequestPart("file")MultipartFile file,@RequestHeader("satoken") String satoken,@RequestPart("uploadActivityDTO")UploadActivityDTO uploadActivityDTO) throws IOException {
        // 校验登录
        Result result = SaPermission.checkSaPermission(satoken);
        if(result.getCode() != 200) {
            return result;
        }
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

    /**
     * @description:  查看项目的 活动资料
     * @param:
     * @return:
     * @author 劳威锟
     * @date: 2023/3/18 3:42 PM
     */
    @PostMapping("getFormDataListPage/{current}/{limit}")
    public Result getFormDataListPage(@PathVariable long current, @PathVariable long limit, @RequestBody(required = false) FormQuery formQuery, @RequestHeader("satoken") String satoken) {
        // 校验登录
        Result result = SaPermission.checkSaPermission(satoken);
        if(result.getCode() != 200) {
            return result;
        }
        /** sql limit分页和前端分页逻辑稍有差别 **/
        /** 分页计算，如果当前页（current）小于等于1，给xml处理时就变为0，意思是过滤0条数据 **/
        /** 分页计算，如果当前页（current）大于1，给xml处理时就自-1 然后乘limit，才能得到当前页所显示的数据 **/
        if(current <= 1) {
            current = 0;
        } else {
            current = (current -1) * limit;
        }
        List<ActivityVo> projectActivityList = sysActivityService.getProjectActivityList(current, limit, formQuery.getId());
        Long total = sysActivityService.getTotalActivityCount(formQuery.getId());
        /** total 为所有记录 **/
        return result.data("list",projectActivityList).data("total",total);
    }

    @PostMapping("getAuditList/{current}/{limit}")
    public Result getAuditList(@PathVariable long current, @PathVariable long limit, @RequestBody(required = false) FormQuery formQuery, @RequestHeader("satoken") String satoken) {
        // 校验登录
        Result result = SaPermission.checkSaPermission(satoken);
        if(result.getCode() != 200) {
            return result;
        }
        /**创建page对象**/
        Page<SysActivity> ceePage = new Page<>(current,limit);
        /**构建条件**/
        QueryWrapper<SysActivity> queryWrapper =new QueryWrapper<>();
        // 根据录入id查询
        queryWrapper.eq("enter_id",formQuery.getId());
        //排序 根据创建时间降序
        queryWrapper.orderByAsc("id");
        sysActivityService.page(ceePage,queryWrapper);
        /**total 为所有记录**/
        long total = ceePage.getTotal();
        List<SysActivity> list= ceePage.getRecords();
        return result.data("rows",list).data("total",total);
    }

    /**
     * @description: 插入单条 活动资料
     * @param:
     * @return:
     * @author 劳威锟
     * @date: 2023/3/17 4:37 PM
     */
    @PostMapping("InterActivity")
    @ResponseBody
    public Result InterActivity(@RequestBody InsertActivityDTO insertActivityDTO, @RequestHeader("satoken") String satoken){
        // 校验登录
        Result result = SaPermission.checkSaPermission(satoken);
        if(result.getCode() != 200) {
            return result;
        }
        SysEnterActivity sysEnterActivity = new SysEnterActivity();
        // 录入项目ID
        sysEnterActivity.setEnterProjectId(insertActivityDTO.getEnterProjectId());
        // 录入项目名称
        sysEnterActivity.setEnterName(insertActivityDTO.getName());
        // 录入人员id
        sysEnterActivity.setEnterUserId(insertActivityDTO.getEnterUserId());
        // 录入人员姓名
        sysEnterActivity.setEnterUserName(insertActivityDTO.getEnterUserName());
        // 录入人员姓名
        sysEnterActivity.setEnterJobNo(insertActivityDTO.getEnterJobNo());
        // 起始时间
        sysEnterActivity.setBeginTime(insertActivityDTO.getBeginTime());
        sysEnterActivity.setEndTime(insertActivityDTO.getEndTime());
        // 默认状态未审核
        sysEnterActivity.setStatus(0);
        sysEnterActivity.setEnterTime(new Date());
        boolean save = sysEnterActivityService.save(sysEnterActivity);
        if (save) {
            SysActivity sysActivity = new SysActivity();
            sysActivity.setEnterId(sysEnterActivity.getId());
            sysActivity.setJobNo(insertActivityDTO.getJobNo());
            sysActivity.setParticipantName(insertActivityDTO.getParticipantName());
            sysActivity.setCertificate(insertActivityDTO.getCertificate());
            sysActivity.setBeRewardedTime(insertActivityDTO.getBeRewardedTime());
            boolean saveFlag = sysActivityService.save(sysActivity);
            if(saveFlag) {
                return result.data("sysEnterActivity",sysEnterActivity).data("sysActivity",sysActivity);
            } else  {
                return result.msg("添加失败");
            }
        } else {
            return result.msg("添加失败");
        }
    }


}

