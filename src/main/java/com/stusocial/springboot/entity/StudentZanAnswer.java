package com.stusocial.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@Data
public class StudentZanAnswer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "student_id")
    private Student student;


    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "answer_id")
    private Answer answer;

    public StudentZanAnswer() {
    }

    public StudentZanAnswer(Student student, Answer answer) {
        this.student = student;
        this.answer = answer;
    }

    public String toString() {
        return "id:" + id;
    }
}
