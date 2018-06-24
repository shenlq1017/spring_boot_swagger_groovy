package com.lic.portal.swagger.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("学生")
class Student {

    @ApiModelProperty("学生编号")
    String id;

    @ApiModelProperty("姓名")
    String name;

    @ApiModelProperty("年龄")
    int age;

    @ApiModelProperty("班级")
    Classroom classroom;

    Student() {
    }

    Student(String id, String name, int age, Classroom classroom) {
        this.id = id
        this.name = name
        this.age = age
        this.classroom = classroom
    }
}
