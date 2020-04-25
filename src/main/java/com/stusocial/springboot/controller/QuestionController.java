package com.stusocial.springboot.controller;

import com.stusocial.springboot.api.Repose.QuestionRepApi;
import com.stusocial.springboot.entity.Question;
import com.stusocial.springboot.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/stusocial/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/byCommunityId")
    @ResponseBody
    public List<Question> getQuestionsByCommunityId(@RequestParam(value = "id") Integer id, @RequestParam(value = "page") Integer page, @RequestParam(value = "pagesize") Integer pagesize) {
        return questionService.getQuestionsByCommunityId(id, page, pagesize);
    }

    @RequestMapping(value = "/add")
    public Question addQuestion(@RequestParam(value = "communityid") Integer communityId, @RequestParam(value = "studentid") Integer studentId,
                                @RequestParam(value = "content") String content) {
        return questionService.addQuestion(content, communityId, studentId);
    }

    @GetMapping(value = "/{id}")
    public Question getQuestionById(@PathVariable(value = "id") Integer id) {
        return questionService.getQuestionById(id);
    }

    @GetMapping(params = {"category", "page", "pagesize"})
    public List<QuestionRepApi> getQuestionsByCategory(@RequestParam("category") String category, @RequestParam("page") Integer page, @RequestParam("pagesize") Integer pagesize) {
        List<QuestionRepApi> questionRepApis = questionService.getQuestionsByCategory(category, page, pagesize);
        return questionRepApis;
    }

    @GetMapping(params = {"sid", "page", "pagesize"})
    public List<Question> getQuestionsByStudent(@RequestParam("sid") Integer sid, @RequestParam("page") Integer page, @RequestParam("pagesize") Integer pagesize) {
        return questionService.getQuestionsByStudent(sid, page, pagesize);
    }

    @DeleteMapping("/{id}")
    public String deleteQuestionById(@PathVariable("id") Integer id) {
        return questionService.deleteQuestionById(id);
    }
}
