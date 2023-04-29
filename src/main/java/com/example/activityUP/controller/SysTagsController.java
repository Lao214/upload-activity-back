package com.example.activityUP.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.activityUP.entity.DTO.InsertTagsDTO;
import com.example.activityUP.entity.SaUser;
import com.example.activityUP.entity.SysTags;
import com.example.activityUP.entity.Vo.FormQuery;
import com.example.activityUP.entity.Vo.TagQuery;
import com.example.activityUP.service.SysTagsService;
import com.example.activityUP.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *   前端控制器
 * </p>
 *
 * @author 劳威锟
 * @since 2023-04-23
 */
@RestController
@RequestMapping("/AU/tags")
public class SysTagsController {


    @Autowired
    private SysTagsService sysTagsService;

    @PostMapping("findTags")
    public Result findTags(@RequestBody(required = false) TagQuery tagQuery) {
        QueryWrapper<SysTags> queryWrapper =new QueryWrapper<SysTags>();
        /** 点击type进行查询 **/
        if(tagQuery.getType() == 1) {
            queryWrapper.eq("parent_id", tagQuery.getParentId());
            List<SysTags> tagList = sysTagsService.list(queryWrapper);
            return  Result.success().data("row",tagList);
        }
        /** 点击Search type搜索 **/
        if(tagQuery.getType() == 2) {
            if(StringUtils.isNotBlank(tagQuery.getSearchType())){
                queryWrapper.like("keyword_cn",tagQuery.getSearchType());
            }
            queryWrapper.eq("level",1);
            List<SysTags> typeList = sysTagsService.list(queryWrapper);
            return  Result.success().data("row",typeList);
        }
        /** 点击Search tags搜索 **/
        if(tagQuery.getType() == 3) {
            if(StringUtils.isNotBlank(tagQuery.getSearchTag())){
                queryWrapper.like("keyword_cn",tagQuery.getSearchTag());
            }
            if(tagQuery.getParentId() != null && tagQuery.getParentId() > 0) {
                queryWrapper.eq("parent_id", tagQuery.getParentId());
            }
            queryWrapper.eq("level",2);
            List<SysTags> tagList = sysTagsService.list(queryWrapper);
            return Result.success().data("row",tagList);
        }
        return Result.success();
    }

    @GetMapping("findAll")
    public Result findAll() {
        List<SysTags> list = sysTagsService.list();
        List<SysTags> tags = new ArrayList<>();
        List<SysTags> type = new ArrayList<>();
        for (SysTags tag: list) {
            if (tag.getParentId() == 0) {
                type.add(tag);
            } else {
                tags.add(tag);
            }
        }
        return Result.success().data("tags",tags).data("type",type);
    }

    @PostMapping("addTags")
    public Result addTags(@RequestBody(required = false) InsertTagsDTO summitTags) {
        List<SysTags> addList = new ArrayList<>();
        if(summitTags.getSummitType() == 0) {
            for (SysTags tag: summitTags.getTagsList()) {
                tag.setLevel(2);
                tag.setParentId(summitTags.getTypeId());
                if(StringUtils.isNotBlank(tag.getKeywordCn()) && StringUtils.isNotBlank(tag.getKeywordEn())) {
                    addList.add(tag);
                }
            }
            boolean b = sysTagsService.saveBatch(addList);
            if(b) {
                return Result.success().msg("添加成功");
            } else {
                return Result.error().msg("添加失败");
            }
        } else if(summitTags.getSummitType() == 1) {
            SysTags sysTags =new SysTags();
            sysTags.setParentId(0l);
            sysTags.setLevel(1);
            sysTags.setKeywordCn(summitTags.getType());
            boolean save = sysTagsService.save(sysTags);
            if(save) {
                for (SysTags tag: summitTags.getTagsList()) {
                    tag.setLevel(2);
                    tag.setParentId(sysTags.getId());
                    if(StringUtils.isNotBlank(tag.getKeywordCn()) && StringUtils.isNotBlank(tag.getKeywordEn())) {
                        addList.add(tag);
                    }
                }
                sysTagsService.saveBatch(addList);
                return Result.success().msg("添加成功");
            } else  {
                return Result.error().msg("添加失败");
            }
        }
        return Result.success();
    }


    /**
     * 文件上传 批量插入用户
     * <p>1. 创建excel对应的实体对象 参照{@link }
     * <p>2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link UploadDataListener}
     * <p>3. 直接读即可
     */
    @PostMapping("upload")
    @ResponseBody
    public Result upload(@RequestPart("file") MultipartFile file) throws IOException {
        // 校验登录
        try {
            EasyExcel.read(file.getInputStream(), SysTags.class, new UploadTagListener(sysTagsService)).sheet().doRead();
        } catch (Exception e) {
            // 处理异常，比如记录日志，返回错误信息等
            return Result.error().msg("导入失败：请查看Excel格式");
        }
        return Result.success().msg("导入成功");
    }
}

