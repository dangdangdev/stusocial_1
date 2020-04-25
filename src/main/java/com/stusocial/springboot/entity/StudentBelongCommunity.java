package com.stusocial.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@Data
public class StudentBelongCommunity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer privilege;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "student_id")
    private Student student;


    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "community_id")
    private Community community;

    public StudentBelongCommunity() {
    }

    public StudentBelongCommunity(Student student, Community community, Integer privilege) {
        this.student = student;
        this.community = community;
        this.privilege = privilege;
    }

    public String toString() {
        return "id:" + id;
    }


}
