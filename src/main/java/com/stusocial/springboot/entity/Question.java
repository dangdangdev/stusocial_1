package com.stusocial.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
@Data
public class Question implements Serializable {
    @Column
    private Integer maxZanAnswerId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String content;
    @Column
    private Integer record;  //用户查看次数，默认为0
    @Column
    private Integer answerCount;
    @Column
    private String category;


    //和学生的关系
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "student_id")
    private Student student;

    //Question和community 是多对1的关系
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "community_id")
    private Community community;

    @JsonIgnore
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Answer> answerList;

    public Question() {

    }

    public Question(String content, Student student, Community community, String category) {
        this.category = category;
        this.content = content;
        this.student = student;
        this.community = community;
        this.answerCount = 0;
        this.record = 0;
    }

    public String toString() {
        return "id:" + id;
    }
}
