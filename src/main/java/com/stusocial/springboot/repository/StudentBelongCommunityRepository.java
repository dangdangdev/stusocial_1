package com.stusocial.springboot.repository;

import com.stusocial.springboot.entity.Community;
import com.stusocial.springboot.entity.Student;
import com.stusocial.springboot.entity.StudentBelongCommunity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface StudentBelongCommunityRepository extends JpaRepository<StudentBelongCommunity, Integer> {
    public List<StudentBelongCommunity> findByCommunityAndPrivilege(Community c, Integer privilege);

    public StudentBelongCommunity findByCommunityAndStudent(Community c, Student s);

    public Integer deleteByStudentAndCommunity(Student student, Community community);

    public List<StudentBelongCommunity> findByCommunity(Community com);

    public List<StudentBelongCommunity> findByStudentAndPrivilege(Student student, Integer privilege, Pageable pageable);
}
