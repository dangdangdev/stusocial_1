package com.stusocial.springboot.service;

import com.stusocial.springboot.api.Request.AnswerApi;
import com.stusocial.springboot.entity.Answer;
import com.stusocial.springboot.entity.Question;
import com.stusocial.springboot.entity.Student;
import com.stusocial.springboot.repository.AnswerReposity;
import com.stusocial.springboot.repository.QuestionRepository;
import com.stusocial.springboot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class AnswerService {
    @Autowired
    private AnswerReposity answerReposity;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private StudentRepository studentRepository;


    public List<Answer> getAnswersByQuestion(Integer id, Integer page, Integer pagesize) {
        Question question = questionRepository.findById(id).get();
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, pagesize, sort);
        List<Answer> answers = answerReposity.findAnswersByQuestion(question, pageable);
        return answers;
    }

    public List<Answer> getAnswersByStudent(Integer sid, Integer page, Integer pagesize) {
        Student student = studentRepository.findById(sid).get();
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, pagesize, sort);
        List<Answer> answers = answerReposity.findAnswersByStudent(student, pageable);
        return answers;
    }

    //在QuesitonRepo加了悲观锁
    public Answer addAnswer(AnswerApi answerApi) {
        Question question = questionRepository.findById(answerApi.getQuestionId()).get();
        Student student = studentRepository.findById(answerApi.getStudentId()).get();
        Answer answer = new Answer(answerApi.getContent(), student, question);
        Answer answerSaved = answerReposity.save(answer);
        question.setAnswerCount(question.getAnswerCount() + 1);
        if (question.getMaxZanAnswerId() == null) {
            question.setMaxZanAnswerId(answerSaved.getId());
        }
        questionRepository.save(question);
        return answerSaved;
    }

    public Answer getAnswerById(Integer id) {
        Answer answer = answerReposity.findById(id).get();
        return answer;
    }

    public String deleteAnswerById(Integer id) {    //删除答案时要注意查看该答案是不是该问题的最高赞回答，如果是的话要查询替换
        Answer answer = answerReposity.findById(id).get();
        Question question = answer.getQuestion();
        question.setAnswerCount(question.getAnswerCount() - 1);
        if (question.getMaxZanAnswerId().equals(id)) {
            List<Answer> answers = question.getAnswerList();
            Answer maxZanAnswer = new Answer();
            Answer tempAnswer = null;
            maxZanAnswer.setZanCount(-1);
            for (Answer answer1 : answers) {
                if(answer1.getId()==answer.getId()){
                    tempAnswer = answer1;
                    continue;
                }
                if (answer1.getZanCount() > maxZanAnswer.getZanCount()) {
                    maxZanAnswer = answer1;
                }
            }
            answers.remove(tempAnswer);   //从questions对应的answers里删掉该answer
            if (maxZanAnswer.getZanCount() != -1) {
                question.setMaxZanAnswerId(maxZanAnswer.getId());
            } else {
                question.setMaxZanAnswerId(null);
            }
        }
        answerReposity.deleteById(id);
        questionRepository.save(question);
        return "success";
    }
}

