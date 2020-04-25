package com.stusocial.springboot.repository;

import com.stusocial.springboot.entity.Student;
import com.stusocial.springboot.entity.StudentFollow;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.web.PageableArgumentResolver;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentFollowRepository extends JpaRepository<StudentFollow, Integer> {
    StudentFollow findByFromStudentAndToStudent(Student fromStudent, Student toStudent);

    Integer deleteByFromStudentAndToStudent(Student fromStudent, Student toStudent);

    List<StudentFollow> findByFromStudent(Student fromStudent, Pageable pageable);
}
