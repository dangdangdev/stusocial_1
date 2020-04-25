package com.stusocial.springboot.controller;

import com.stusocial.springboot.api.Request.ReplyApi;
import com.stusocial.springboot.entity.Comment;
import com.stusocial.springboot.entity.Reply;
import com.stusocial.springboot.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stusocial")
public class ReplyController {
    @Autowired
    private ReplyService replyService;

    @GetMapping("/replies/comment/{id}")
    public List<Reply> getRepliesByComment(@PathVariable("id") Integer cid) {
        return replyService.getRepliesByComment(cid);
    }

    @PostMapping("/reply")
    public Reply addReply(@RequestBody ReplyApi replyApi) {
        return replyService.addReply(replyApi);
    }
}
