package com.stusocial.springboot.dto;

import lombok.Data;

@Data
public class ReplyDto {
    private String content;
    private Integer studentId;
    private Integer commentId;
}
