package com.example.activityUP.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.activityUP.entity.SaUser;
import com.example.activityUP.entity.SysTags;
import com.example.activityUP.entity.Vo.FormQuery;
import com.example.activityUP.service.SysTagsService;
import com.example.activityUP.utils.Result;
import com.example.activityUP.utils.SaPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
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
    public Result findTags(@RequestBody(required = false) FormQuery formQuery) {
        QueryWrapper<SysTags> queryWrapper =new QueryWrapper<SysTags>();
        List<SysTags> list = sysTagsService.list();
        return Result.success().data("row",list);
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
    public Result addTags(@RequestBody(required = false) SysTags sysTags) {
        // 表示为type 而不是tag
        if(sysTags.getParentId() == null || sysTags.getParentId() <= 0) {
            sysTags.setParentId(0l);
        }
        boolean save = sysTagsService.save(sysTags);
        if (save) {
            return Result.success().msg("添加成功");
        } else {
            return Result.error().msg("添加失败");
        }

    }

}

