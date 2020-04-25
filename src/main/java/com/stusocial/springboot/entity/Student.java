package com.stusocial.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.JoinColumnOrFormula;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

//使用jpa注解配置映射关系
@Entity
@Table
@Data
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String university;
    @Column
    private String openid;
    @Column
    private String avatarUrl;

    @JsonIgnore
    @OneToMany(mappedBy = "toStudent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StudentFollow> toStudentFollowList;

    @JsonIgnore
    @OneToMany(mappedBy = "fromStudent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StudentFollow> fromStudentFollowList;

    @JsonIgnore
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Question> questionList;

    @JsonIgnore
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> commentList;

    @JsonIgnore
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Answer> answersList;

    @JsonIgnore
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reply> replyList;

    @JsonIgnore
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StudentZanAnswer> studentZanAnswerList;

    @JsonIgnore
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StudentZanComment> studentZanCommentList;

    @JsonIgnore
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StudentBelongCommunity> studentBelongCommunityList;

    public Student() {
    }

    public Student(String name, String avatarUrl, String openid, String university) {
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.openid = openid;
        this.university = university;
    }

    public String toString() {
        return "id:" + id;
    }
}
