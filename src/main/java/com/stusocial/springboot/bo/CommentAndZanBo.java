package com.stusocial.springboot.bo;

import com.stusocial.springboot.entity.Comment;
import lombok.Data;

import java.util.List;

@Data
public class CommentAndZanBo {
    private List<Comment> commentList;
    private boolean[] commentsZanStatus;
}
