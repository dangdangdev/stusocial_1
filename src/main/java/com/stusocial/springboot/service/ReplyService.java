package com.stusocial.springboot.service;

import com.stusocial.springboot.dto.ReplyDto;
import com.stusocial.springboot.entity.Comment;
import com.stusocial.springboot.entity.Reply;
import com.stusocial.springboot.entity.Student;
import com.stusocial.springboot.repository.CommentRepository;
import com.stusocial.springboot.repository.ReplyRepository;
import com.stusocial.springboot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false, rollbackFor = Throwable.class)
public class ReplyService {
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private StudentRepository studentRepository;

    public List<Reply> getRepliesByComment(Integer cid) {
        Comment comment = commentRepository.findById(cid).get();
        return replyRepository.findByComment(comment);
    }

    public Reply addReply(ReplyDto replyDto) {
        Student student = studentRepository.findById(replyDto.getStudentId()).get();
        Comment comment = commentRepository.findById(replyDto.getCommentId()).get();
        comment.setReplyCount(comment.getReplyCount() + 1);
        commentRepository.save(comment);
        Reply reply = new Reply(replyDto.getContent(), student, comment);
        return replyRepository.save(reply);
    }
}
