package com.stusocial.springboot.api.Request;

import lombok.Data;

@Data
public class CommentApi {
    private String content;
    private Integer studentId;
    private Integer answerId;


    public CommentApi(String content, Integer studentId, Integer answerId) {
        this.studentId = studentId;
        this.answerId = answerId;
        this.content = content;
    }
}
