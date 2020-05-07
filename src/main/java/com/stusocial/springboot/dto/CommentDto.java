package com.stusocial.springboot.dto;

import lombok.Data;

@Data
public class CommentDto {
    private String content;
    private Integer studentId;
    private Integer answerId;


    public CommentDto(String content, Integer studentId, Integer answerId) {
        this.studentId = studentId;
        this.answerId = answerId;
        this.content = content;
    }
}
