package com.example.activityUP.controller;


import com.example.activityUP.entity.SaMenu;
import com.example.activityUP.service.SaMenuService;
import com.example.activityUP.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 劳威锟
 * @since 2023-03-14
 */
@RestController
@RequestMapping("/AU/saMenu")
public class SaMenuController {

    @Autowired
    private SaMenuService saMenuService;

//    @ApiOperation("菜单列表")
    @GetMapping("findNodes")
    public Result findNodes() {
        List<SaMenu> list = saMenuService.findNodes();
        return Result.success().data("list",list);
    }


}

