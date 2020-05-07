package com.stusocial.springboot.dto;

import lombok.Data;

@Data
public class AnswerDto {
    private String content;
    private Integer studentId;
    private Integer questionId;



    public AnswerDto(String content, Integer studentId, Integer questionId) {
        this.content = content;
        this.studentId = studentId;
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }
}
