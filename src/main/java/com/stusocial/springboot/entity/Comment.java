package com.stusocial.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
@Data
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String content;

    @Column
    private Integer zanCount;
    @Column
    private Integer replyCount;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "student_id")
    private Student student;


    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "answer_id")
    private Answer answer;

    @JsonIgnore
    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StudentZanComment> studentZanCommentList;

    @JsonIgnore
    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reply> replyList;

    public Comment() {
    }

    public Comment(String content, Student student, Answer answer) {
        this.content = content;
        this.student = student;
        this.answer = answer;
        this.zanCount = 0;
        this.replyCount = 0;
    }

    public String toString() {
        return "id:" + id;
    }
}
