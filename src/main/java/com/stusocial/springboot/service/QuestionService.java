package com.stusocial.springboot.service;

import com.stusocial.springboot.api.Repose.QuestionRepApi;
import com.stusocial.springboot.entity.*;
import com.stusocial.springboot.repository.*;
import com.stusocial.springboot.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private CommunityRepository communityRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AnswerReposity answerReposity;

    public List<Question> getQuestionsByCommunityId(Integer id, Integer page, Integer pagesize) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(page, pagesize, sort);
        Community com = communityRepository.findById(id).get();
        List<Question> questions = questionRepository.findQuestionsByCommunity(com, pageable);
        return questions;
    }


    //这里在CommunityRepo那里加了悲观锁
    public Question addQuestion(String content, Integer communityId, Integer studentId) {
        Community com = communityRepository.findById(communityId).get();
        Student stu = studentRepository.findById(studentId).get();
        Question question = new Question(content, stu, com, com.getCategory());
        com.setQuestionCount(com.getQuestionCount() + 1);  //社区对应的问题加1
        communityRepository.save(com);
        return questionRepository.save(question);
    }

    public Question getQuestionById(Integer id) {
        Question question = questionRepository.findById(id).get();
        question.setRecord(question.getRecord() + 1);
        questionRepository.save(question);
        return question;
    }

    public List<QuestionRepApi> getQuestionsByCategory(String category, int page, int pageSize) {
        Sort sort = null;
        if (category.equals("热门")) {
            sort = Sort.by(Sort.Direction.DESC, "record");
        } else {
            sort = Sort.by(Sort.Direction.DESC, "id");
        }
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        List<Question> questions = null;
        if (category.equals("热门")) {
            Page pageQuestions = questionRepository.findAll(pageable);
            questions = pageQuestions.toList();
        } else {
            questions = questionRepository.findQuestionsByCategory(category, pageable);
        }
        List<QuestionRepApi> questionRepApis = new ArrayList<>();
        for (Question question : questions) {
            Integer maxZanAnswerId = question.getMaxZanAnswerId();
            Answer maxZanAnswer = null;
            if (maxZanAnswerId != null) {     //这里是由问题的，没有考虑并发，如果这时该最高赞答案被替换或被删除时正好进行到这一步，那么就会出现问题，应该要写sql，要改下，以后再看
                maxZanAnswer = answerReposity.findById(maxZanAnswerId).get();
                QuestionRepApi repApi = new QuestionRepApi(question, maxZanAnswer);
                questionRepApis.add(repApi);
            }
        }
        return questionRepApis;
    }

    public List<Question> getQuestionsByStudent(Integer sid, Integer page, Integer pagesize) {
        Student student = studentRepository.findById(sid).get();
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(page, pagesize, sort);
        return questionRepository.findQuestionsByStudent(student, pageable);
    }

    public String deleteQuestionById(Integer id) {
        Question question = questionRepository.findById(id).get();
        Community com = question.getCommunity();
        com.setQuestionCount(com.getQuestionCount() - 1);
        communityRepository.save(com);
        questionRepository.deleteById(id);
        return "success";
    }

}
