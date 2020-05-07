package com.stusocial.springboot.bo;

import com.stusocial.springboot.entity.Answer;
import com.stusocial.springboot.entity.Question;
import lombok.Data;

@Data
public class QuestionRepBo {
    private Question question;
    private Answer maxZanAnswer;

    public QuestionRepBo(Question question, Answer maxZanAnswer) {
        this.question = question;
        this.maxZanAnswer = maxZanAnswer;
    }
}
