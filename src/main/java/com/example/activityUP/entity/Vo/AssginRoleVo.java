package com.example.activityUP.entity.Vo;



import com.example.activityUP.entity.SaRole;
import lombok.Data;

import java.util.List;


@Data
public class AssginRoleVo {

    //(value = "用户id")
    private Long userId;

    //(value = "角色id列表")
    private List<String> roleIdList;

    private List<String> roleNameList;

}
