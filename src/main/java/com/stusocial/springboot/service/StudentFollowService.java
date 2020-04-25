package com.stusocial.springboot.service;

import com.stusocial.springboot.entity.Student;
import com.stusocial.springboot.entity.StudentFollow;
import com.stusocial.springboot.repository.StudentFollowRepository;
import com.stusocial.springboot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentFollowService {
    @Autowired
    private StudentFollowRepository studentFollowRepository;
    @Autowired
    private StudentRepository studentRepository;

    public StudentFollow addStudentFollow(Integer fromStuId, Integer toStuId) {
        Student fromStudent = studentRepository.findById(fromStuId).get();
        Student toStudent = studentRepository.findById(toStuId).get();
        StudentFollow studentFollow = new StudentFollow(fromStudent, toStudent);
        return studentFollowRepository.save(studentFollow);
    }

    public Integer deleteStudentFollow(Integer fromStuId, Integer toStuId) {
        Student fromStudent = studentRepository.findById(fromStuId).get();
        Student toStudent = studentRepository.findById(toStuId).get();
        return studentFollowRepository.deleteByFromStudentAndToStudent(fromStudent, toStudent);
    }

    public StudentFollow getStudentFollow(Integer fromStuId, Integer toStuId) {
        Student fromStudent = studentRepository.findById(fromStuId).get();
        Student toStudent = studentRepository.findById(toStuId).get();
        return studentFollowRepository.findByFromStudentAndToStudent(fromStudent, toStudent);
    }

    public List<StudentFollow> getStudentFollowsByFromStudent(Integer sid,Integer page,Integer pagesize){
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, pagesize, sort);
        Student fromStudent = studentRepository.findById(sid).get();
        return studentFollowRepository.findByFromStudent(fromStudent,pageable);
    }
}
