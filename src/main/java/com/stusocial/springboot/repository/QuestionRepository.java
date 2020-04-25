package com.stusocial.springboot.repository;

import com.stusocial.springboot.entity.Community;
import com.stusocial.springboot.entity.Question;
import com.stusocial.springboot.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    public List<Question> findQuestionsByCommunity(Community com, Pageable pageble);

    @Lock(LockModeType.PESSIMISTIC_WRITE) //不会redis只能用悲观锁
    public Optional<Question> findById(Integer id);

    @Lock(LockModeType.PESSIMISTIC_WRITE) //不会redis只能用悲观锁
    public List<Question> findQuestionsByCategory(String category, Pageable pageable);

    public List<Question> findQuestionsByStudent(Student student, Pageable pageable);
}
