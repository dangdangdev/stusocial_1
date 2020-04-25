package com.stusocial.springboot.controller;

import com.stusocial.springboot.entity.Answer;
import com.stusocial.springboot.entity.StudentZanComment;
import com.stusocial.springboot.service.StudentZanCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stusocial/studentzancomment")
public class StudentZanCommentController {
    @Autowired
    private StudentZanCommentService studentZanCommentService;

    @PostMapping
    public StudentZanComment addStudentZanComment(@RequestParam(value = "sid") Integer sid, @RequestParam(value = "cid") Integer cid) {
        return studentZanCommentService.addStudentZanComment(sid, cid);
    }

    @DeleteMapping
    public Integer deleteStudentZanAnswer(@RequestParam(value = "sid") Integer sid, @RequestParam(value = "cid") Integer cid) {
        return studentZanCommentService.deleteStudentZanComment(sid, cid);
    }
}
