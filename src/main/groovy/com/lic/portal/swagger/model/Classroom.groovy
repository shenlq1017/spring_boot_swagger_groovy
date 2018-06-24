package com.lic.portal.swagger.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty


@ApiModel("班级")
class Classroom {
    @ApiModelProperty("id")
    String id;
    @ApiModelProperty("班级名称")
    String roomName;
    @ApiModelProperty("班级编号")
    int roomNum;

    Classroom() {
    }

    Classroom(String id, String roomName, int roomNum) {
        this.id = id
        this.roomName = roomName
        this.roomNum = roomNum
    }
}
