package com.stusocial.springboot.service;

import com.stusocial.springboot.entity.Community;
import com.stusocial.springboot.entity.Student;
import com.stusocial.springboot.entity.StudentBelongCommunity;
import com.stusocial.springboot.repository.CommunityRepository;
import com.stusocial.springboot.repository.StudentBelongCommunityRepository;
import com.stusocial.springboot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class StudentBelongCommunityService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CommunityRepository communityRepository;
    @Autowired
    private StudentBelongCommunityRepository studentBelongCommunityRepository;


    public StudentBelongCommunity getStuBelongCom(Integer studentid, Integer communityid) {
        Student student = studentRepository.findById(studentid).get();
        Community community = communityRepository.findById(communityid).get();
        StudentBelongCommunity stubecom = studentBelongCommunityRepository.findByCommunityAndStudent(community, student);
        return stubecom;
    }

    public String addStuBelongCom(Integer sid, Integer cid) {
        if (this.getStuBelongCom(sid, cid) != null) {
            return "fail";
        }
        Student student = studentRepository.findById(sid).get();
        Community community = communityRepository.findById(cid).get();
        StudentBelongCommunity studentBelongCommunity = new StudentBelongCommunity(student, community, 1);
        studentBelongCommunityRepository.save(studentBelongCommunity);
        return "success";
    }

    public Integer deleteStuBelongCom(Integer sid, Integer cid) {
        Student student = studentRepository.findById(sid).get();
        Community community = communityRepository.findById(cid).get();
        community.setStudentCount(community.getStudentCount()-1);
        communityRepository.save(community);
        return studentBelongCommunityRepository.deleteByStudentAndCommunity(student, community);
    }

    public List<StudentBelongCommunity> getStuBelongComsByCom(Integer cid) {
        Community community = communityRepository.findById(cid).get();
        return studentBelongCommunityRepository.findByCommunity(community);
    }

    public List<StudentBelongCommunity> getStuBelongComsByStu(Integer sid, Integer page, Integer pagesize, Integer privilege) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, pagesize, sort);
        Student student = studentRepository.findById(sid).get();
        return studentBelongCommunityRepository.findByStudentAndPrivilege(student, privilege, pageable);
    }
}

