package com.stusocial.springboot.repository;

import com.stusocial.springboot.entity.Answer;
import com.stusocial.springboot.entity.Question;
import com.stusocial.springboot.entity.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;


import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerReposity extends JpaRepository<Answer, Integer> {
    public List<Answer> findAnswersByQuestion(Question question, Pageable pageable);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public Optional<Answer> findById(Integer id);

    public List<Answer> findAnswersByStudent(Student student, Pageable pageable);
}
