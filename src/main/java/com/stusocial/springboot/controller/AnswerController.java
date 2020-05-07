package com.stusocial.springboot.controller;

import com.stusocial.springboot.dto.AnswerDto;
import com.stusocial.springboot.entity.Answer;
import com.stusocial.springboot.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/stusocial")
public class AnswerController {
    @Autowired
    AnswerService answerService;

    @GetMapping(value = "/answers/question/{id}")
    public List<Answer> getAnswersByQuestion(@PathVariable(value = "id") Integer id, @RequestParam(value = "page") Integer page, @RequestParam(value = "pagesize") Integer pagesize) {
        return answerService.getAnswersByQuestion(id, page, pagesize);
    }

    @PostMapping(value = "/answer")
    public Answer addAnswer(@RequestBody AnswerDto answerDTO) {
        return answerService.addAnswer(answerDTO);
    }

    @GetMapping(value = "/answer/{id}")
    public Answer getAnswerById(@PathVariable("id") Integer id) {
        return answerService.getAnswerById(id);
    }

    @GetMapping(value = "/answer", params = {"sid", "page", "pagesize"})
    public List<Answer> getAnswersByStudent(@RequestParam("sid") Integer sid, @RequestParam("page") Integer page, @RequestParam("pagesize") Integer pagesize) {
        return answerService.getAnswersByStudent(sid, page, pagesize);
    }

    @DeleteMapping(value = "/answer/{id}")
    public String deleteAnswerById(@PathVariable("id")Integer id){
        return answerService.deleteAnswerById(id);
    }
}
