package com.stusocial.springboot.service;

import com.stusocial.springboot.dto.CommentDto;
import com.stusocial.springboot.entity.Answer;
import com.stusocial.springboot.entity.Comment;
import com.stusocial.springboot.entity.Student;
import com.stusocial.springboot.repository.AnswerReposity;
import com.stusocial.springboot.repository.CommentRepository;
import com.stusocial.springboot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AnswerReposity answerReposity;


    public Comment addComment(CommentDto commentDTO) {
        String content = commentDTO.getContent();
        Student stu = studentRepository.findById(commentDTO.getStudentId()).get();
        Answer ans = answerReposity.findById(commentDTO.getAnswerId()).get();
        Comment comment = new Comment(content, stu, ans);
        ans.setCommentCount(ans.getCommentCount() + 1);
        answerReposity.save(ans);
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByAnswer(Integer aid) {
        Answer ans = answerReposity.findById(aid).get();
        return commentRepository.findByAnswer(ans);
    }
}
