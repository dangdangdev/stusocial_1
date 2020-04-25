package com.stusocial.springboot.api.Request;

import lombok.Data;

@Data
public class ReplyApi {
    private String content;
    private Integer studentId;
    private Integer commentId;
}
