package com.stusocial.springboot.api.Repose;

import com.stusocial.springboot.entity.Comment;
import lombok.Data;

import java.util.List;

@Data
public class CommentAndZanApi {
    private List<Comment> commentList;
    private boolean[] commentsZanStatus;
}
