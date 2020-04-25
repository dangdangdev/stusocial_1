package com.stusocial.springboot.entity;

import java.io.Serializable;

public class ComAndStu implements Serializable {
    private Community community;
    private Student student;

    public ComAndStu(Community community, Student student) {
        this.community = community;
        this.student = student;
    }

    public ComAndStu() {
    }
}
