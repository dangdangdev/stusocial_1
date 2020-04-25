package com.stusocial.springboot.repository;

import com.stusocial.springboot.entity.ComAndStu;
import com.stusocial.springboot.entity.Comment;
import com.stusocial.springboot.entity.Student;
import com.stusocial.springboot.entity.StudentZanComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentZanCommentRepository extends JpaRepository<StudentZanComment, Integer> {
    public StudentZanComment findByStudentAndComment(Student stut, Comment com);

    public Integer deleteByStudentAndComment(Student stu, Comment com);
}
