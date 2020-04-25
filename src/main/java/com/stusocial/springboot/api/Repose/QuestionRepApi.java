package com.stusocial.springboot.api.Repose;

import com.stusocial.springboot.entity.Answer;
import com.stusocial.springboot.entity.Question;
import com.stusocial.springboot.repository.AnswerReposity;
import lombok.Data;

@Data
public class QuestionRepApi {
    private Question question;
    private Answer maxZanAnswer;

    public QuestionRepApi(Question question, Answer maxZanAnswer) {
        this.question = question;
        this.maxZanAnswer = maxZanAnswer;
    }
}
