package com.stusocial.springboot.controller;

import com.stusocial.springboot.api.Repose.CommentAndZanApi;
import com.stusocial.springboot.api.Request.CommentApi;
import com.stusocial.springboot.entity.Comment;
import com.stusocial.springboot.entity.Student;
import com.stusocial.springboot.entity.StudentZanComment;
import com.stusocial.springboot.service.CommentService;
import com.stusocial.springboot.service.StudentService;
import com.stusocial.springboot.service.StudentZanCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/stusocial")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private StudentZanCommentService studentZanCommentService;
    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/comment")
    public Comment addComment(@RequestBody CommentApi commentApi) {
        Comment comment = commentService.addComment(commentApi);
        return comment;
    }

    @GetMapping(value = "/comments/student/{sid}/answer/{aid}")
    public CommentAndZanApi getCommentsByAnswer(@PathVariable("sid") Integer sid, @PathVariable("aid") Integer aid) {
        List<Comment> comments = commentService.getCommentsByAnswer(aid);
        Student student = studentService.getStudentById(sid);
        CommentAndZanApi commentAndZanApi = new CommentAndZanApi();
        if (comments.size() != 0) {
            boolean[] zanStatus = new boolean[comments.size()];
            Arrays.fill(zanStatus, false);
            int index = 0;
            for (Comment comment : comments) {
                StudentZanComment szc = studentZanCommentService.getStudentZanCommentByStuAndCom(student, comment);
                if (szc != null) {
                    zanStatus[index] = true;
                }
                index++;
            }
            commentAndZanApi.setCommentList(comments);
            commentAndZanApi.setCommentsZanStatus(zanStatus);
        }
        return commentAndZanApi;
    }
}
