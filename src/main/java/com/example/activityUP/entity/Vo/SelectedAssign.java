package com.example.activityUP.entity.Vo;

import com.example.activityUP.entity.SaMenu;
import lombok.Data;

import java.util.List;

/**
 * @author peterlin
 * @version 1.0
 * @description: TODO
 * @date 2023/3/15 10:04 AM
 */
@Data
public class SelectedAssign {
    List<SaMenu> menuList;

    List<SaMenu> roleList;

    List<Long> selectedList;
}
