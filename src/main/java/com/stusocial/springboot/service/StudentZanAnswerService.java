package com.stusocial.springboot.service;

import com.stusocial.springboot.entity.Answer;
import com.stusocial.springboot.entity.Question;
import com.stusocial.springboot.entity.Student;
import com.stusocial.springboot.entity.StudentZanAnswer;
import com.stusocial.springboot.repository.AnswerReposity;
import com.stusocial.springboot.repository.QuestionRepository;
import com.stusocial.springboot.repository.StudentRepository;
import com.stusocial.springboot.repository.StudentZanAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class StudentZanAnswerService {
    @Autowired
    StudentZanAnswerRepository studentZanAnswerRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    AnswerReposity answerReposity;
    @Autowired
    AnswerService answerService;
    @Autowired
    QuestionRepository questionRepository;

    public StudentZanAnswer getStudentZanAnswerByStuAndAns(Integer sid, Integer aid) {
        Student student = studentRepository.findById(sid).get();
        Answer answer = answerReposity.findById(aid).get();
        return studentZanAnswerRepository.findByStudentAndAndAnswer(student, answer);
    }

    public Answer addStudentZanAnswer(Integer sid, Integer aid) {
        Student student = studentRepository.findById(sid).get();
        Answer answer = answerReposity.findById(aid).get();
        StudentZanAnswer sza = new StudentZanAnswer(student, answer);
        studentZanAnswerRepository.save(sza);
        answer.setZanCount(answer.getZanCount() + 1);
        answerReposity.save(answer);
        Integer maxZanAnswerId = answer.getQuestion().getMaxZanAnswerId();
        if (maxZanAnswerId != null) {
            Answer maxZanAnswer = answerReposity.findById(maxZanAnswerId).get();
            if (maxZanAnswer.getZanCount() < answer.getZanCount()) {
                Question question = answer.getQuestion();
                question.setMaxZanAnswerId(answer.getId());
                questionRepository.save(question);
            }
        } else {
            Question question = answer.getQuestion();
            question.setMaxZanAnswerId(answer.getId());
            questionRepository.save(question);
        }
        return answer;
    }


    public Answer deleteStudentZanAnswer(Integer sid, Integer aid) {
        Student student = studentRepository.findById(sid).get();
        Answer answer = answerReposity.findById(aid).get();
        studentZanAnswerRepository.deleteByStudentAndAnswer(student, answer);
        answer.setZanCount(answer.getZanCount() - 1);
        answerReposity.save(answer);
        Question question = answer.getQuestion();
        Answer maxZanAnaswer = answerReposity.findById(question.getMaxZanAnswerId()).get();
        if (maxZanAnaswer.getId() == answer.getId()) {
            List<Answer> answers = question.getAnswerList();
            Answer tempAnswer = new Answer();
            tempAnswer.setZanCount(-1);
            for (Answer answer1 : answers) {
                if (answer1.getZanCount() > tempAnswer.getZanCount()) {
                    tempAnswer = answer1;
                }
            }
            question.setMaxZanAnswerId(tempAnswer.getId());
            questionRepository.save(question);
        }
        return answer;
    }
}
