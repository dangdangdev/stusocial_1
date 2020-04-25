package com.stusocial.springboot.repository;

import com.stusocial.springboot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    public Student findByName(String name);

    public Student findByOpenid(String openid);

}
