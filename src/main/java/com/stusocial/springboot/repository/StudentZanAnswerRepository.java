package com.stusocial.springboot.repository;

import com.stusocial.springboot.entity.Answer;
import com.stusocial.springboot.entity.Student;
import com.stusocial.springboot.entity.StudentZanAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentZanAnswerRepository extends JpaRepository<StudentZanAnswer, Integer> {
    public StudentZanAnswer findByStudentAndAndAnswer(Student stu, Answer ans);

    public Integer deleteByStudentAndAnswer(Student stu, Answer ans);
}
