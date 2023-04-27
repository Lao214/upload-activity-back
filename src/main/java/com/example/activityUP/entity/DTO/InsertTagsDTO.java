package com.example.activityUP.entity.DTO;

import com.example.activityUP.entity.SysTags;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author peterlin
 * @version 1.0
 * @description: TODO
 * @date 2023/4/26 4:29 PM
 */
@Data
public class InsertTagsDTO {

    List<SysTags> tagsList;

    String type;

    Long typeId;

    Integer summitType;
}
