package com.lic.portal.swagger.controller

import com.lic.portal.swagger.model.Classroom
import com.lic.portal.swagger.model.Student
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("rest/student")
@Api(description = "学生信息",tags = "基本信息模块")
class StudentController {


    @GetMapping("/{id}")
    @ApiOperation(value = "获取学生详情",notes = "根据学生编号拿到学生的详细信息")
    Student findStudent(
            @ApiParam("学生编号") @PathVariable String id
    ) {

        Classroom classroom = new Classroom("1","高三二班",302);
        Student student = new Student(id,"张三",25,classroom);
        return student;
    }

    @PutMapping("/{id}")
    @ApiOperation("根据学生编号")
    void updateStudent(
            @ApiParam("学生编号") @PathVariable String id,
            @RequestBody Student student
    ) {
        println("update student");
    }

    @GetMapping
    @ApiOperation(value = "根据条件获取学生集合",notes = "根据名字拿到符合条件的学生数组")
    List<Student> queryByCondition(@ApiParam("姓名") @RequestParam(required = false) String name){
        Classroom classroom = new Classroom("1","高三二班",302);
        Student student = new Student("1","张三",25,classroom);
        List<Student> students = new ArrayList<>();
        students.add(student);

        return students;
    }


}
