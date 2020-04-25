package com.stusocial.springboot.repository;

import com.stusocial.springboot.entity.Answer;
import com.stusocial.springboot.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    public List<Comment> findByAnswer(Answer ans);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public Optional<Comment> findById(Integer id);
}
