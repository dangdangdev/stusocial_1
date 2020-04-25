package com.stusocial.springboot.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@Data
public class StudentFollow implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "from_student_id")
    private Student fromStudent;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "to_student_id")
    private Student toStudent;

    public StudentFollow() {
    }

    public StudentFollow(Student fromStudent, Student toStudent) {
        this.fromStudent = fromStudent;
        this.toStudent = toStudent;
    }
}
