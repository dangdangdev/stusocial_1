package com.stusocial.springboot.service;

import com.stusocial.springboot.entity.Comment;
import com.stusocial.springboot.entity.Student;
import com.stusocial.springboot.entity.StudentZanComment;
import com.stusocial.springboot.repository.CommentRepository;
import com.stusocial.springboot.repository.StudentRepository;
import com.stusocial.springboot.repository.StudentZanCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class StudentZanCommentService {
    @Autowired
    private StudentZanCommentRepository studentZanCommentRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CommentRepository commentRepository;

    public StudentZanComment getStudentZanCommentByStuAndCom(Student student, Comment comment) {
        return studentZanCommentRepository.findByStudentAndComment(student, comment);
    }

    public StudentZanComment addStudentZanComment(Integer sid, Integer cid) {
        Student student = studentRepository.findById(sid).get();
        Comment comment = commentRepository.findById((cid)).get();
        comment.setZanCount(comment.getZanCount() + 1);
        StudentZanComment studentZanComment = new StudentZanComment(student, comment);
        commentRepository.save(comment);
        return studentZanCommentRepository.save(studentZanComment);
    }

    public Integer deleteStudentZanComment(Integer sid, Integer cid) {
        Student student = studentRepository.findById(sid).get();
        Comment comment = commentRepository.findById((cid)).get();
        comment.setZanCount(comment.getZanCount() - 1);
        commentRepository.save(comment);
        return studentZanCommentRepository.deleteByStudentAndComment(student, comment);
    }

}
