package com.stusocial.springboot.controller;

import com.stusocial.springboot.entity.Answer;
import com.stusocial.springboot.entity.StudentZanAnswer;
import com.stusocial.springboot.service.StudentZanAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stusocial/studentzananswer")
public class StudentZanAnswerController {
    @Autowired
    StudentZanAnswerService studentZanAnswerService;

    @GetMapping(value = "/student/{sid}/answer/{aid}")
    public StudentZanAnswer getStudentZanAnswerByStuAndAns(@PathVariable(value = "sid") Integer sid, @PathVariable(value = "aid") Integer aid) {
        return studentZanAnswerService.getStudentZanAnswerByStuAndAns(sid, aid);
    }

    @PostMapping
    public Answer addStudentZanAnswer(@RequestParam(value = "sid") Integer sid, @RequestParam(value = "aid") Integer aid) {
        return studentZanAnswerService.addStudentZanAnswer(sid, aid);
    }

    @DeleteMapping
    public Answer deleteStudentZanAnswer(@RequestParam(value = "sid") Integer sid, @RequestParam(value = "aid") Integer aid) {
        return studentZanAnswerService.deleteStudentZanAnswer(sid, aid);
    }
}
