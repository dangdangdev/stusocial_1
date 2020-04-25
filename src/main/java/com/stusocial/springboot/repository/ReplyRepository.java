package com.stusocial.springboot.repository;

import com.stusocial.springboot.entity.Comment;
import com.stusocial.springboot.entity.Reply;
import com.stusocial.springboot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Integer> {
    public List<Reply> findByComment(Comment comment);

    public List<Reply> findByStudent(Student stu);
}
