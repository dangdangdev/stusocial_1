package com.stusocial.springboot.controller;


import com.stusocial.springboot.entity.StudentFollow;
import com.stusocial.springboot.service.StudentFollowService;
import com.stusocial.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/stusocial/studentfollow")
public class StudentFollowController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentFollowService studentFollowService;

    @PostMapping(params = {"fromStuId", "toStuId"})
    public StudentFollow addStudentFollow(@RequestParam("fromStuId") Integer fromStuId, @RequestParam("toStuId") Integer toStuId) {
        return studentFollowService.addStudentFollow(fromStuId, toStuId);
    }

    @DeleteMapping(params = {"fromStuId", "toStuId"})
    public Integer deleteStudentFollow(@RequestParam("fromStuId") Integer fromStuId, @RequestParam("toStuId") Integer toStuId) {
        return studentFollowService.deleteStudentFollow(fromStuId, toStuId);
    }

    @GetMapping(params = {"fromStuId", "toStuId"})
    public StudentFollow getStudentFollow(@RequestParam("fromStuId") Integer fromStuId, @RequestParam("toStuId") Integer toStuId) {
        return studentFollowService.getStudentFollow(fromStuId, toStuId);
    }

    @GetMapping("/fromstudent/{id}")
    public List<StudentFollow> getStudentFollowsByFromStudent(@PathVariable("id")Integer sid,@RequestParam("page")Integer page,@RequestParam("pagesize")Integer pagesize){
        return studentFollowService.getStudentFollowsByFromStudent(sid,page,pagesize);
    }
}
