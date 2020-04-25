package com.stusocial.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.websocket.ClientEndpoint;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
@Data
public class Community implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Integer studentCount;
    @Column
    private Integer questionCount;
    @Column
    private String category;
    @Column
    private String unversity;
    @Column
    private String name;
    @Column
    private String content;

    @Column
    private Integer imgCount;

    @JsonIgnore//方便查询用的
    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Question> questionList;

    @JsonIgnore
    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StudentBelongCommunity> studentBelongCommunityList;


    public Community() {
    }

    public Community(String category, String unversity, String name, String content, Integer imgCount) {
        this.category = category;
        this.unversity = unversity;
        this.name = name;
        this.content = content;
        this.imgCount = imgCount;
        this.questionCount = 0;
        this.studentCount = 1;
    }


}
