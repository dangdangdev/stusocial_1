package com.stusocial.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@Data
public class Reply implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String content;

    @Column
    private Integer zanCount;
    //Question和community 是多对1的关系
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "student_id")
    private Student student;

    //Question和community 是多对1的关系
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    public Reply() {
    }


    public Reply(String content, Student student, Comment comment) {
        this.content = content;
        this.student = student;
        this.comment = comment;
        this.zanCount = 0;
    }

    public String toString() {
        return "id:" + id;
    }
}
