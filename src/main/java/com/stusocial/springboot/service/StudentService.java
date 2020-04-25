package com.stusocial.springboot.service;

import com.alibaba.fastjson.JSONObject;
import com.stusocial.springboot.entity.Student;
import com.stusocial.springboot.repository.StudentRepository;
import com.stusocial.springboot.utils.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private String baseUrl = "https://api.weixin.qq.com/sns/jscode2session";
    private String paramsHead = "appid=wx86d519867a6e52d8&secret=45502553b6e95e873188f76b27174baf&js_code=";
    private String grantString = "&grant_type=authorization_code";
    @Autowired
    private StudentRepository studentRepository;

    public Student getStudentByName(String name) {
        Student student = studentRepository.findByName(name);
        if (student == null) {
            Student student1 = new Student();
            student1.setName(name);
            return studentRepository.save(student1);
        }
        return student;
    }

    public Student getStudentById(Integer id) {
        return studentRepository.getOne(id);
    }

    public Student update(Integer id, String university) {
        Student s = this.getStudentById(id);
        s.setUniversity(university);
        return studentRepository.save(s);
    }

    public Student register(Student s) {
        return studentRepository.save(s);
    }

    public Student login(String code) {
        String url = this.baseUrl;
        String params = this.paramsHead + code + this.grantString;
        String result = HttpRequest.sendGet(url, params);

        JSONObject jsonObject = JSONObject.parseObject(result);
        String openid = (String) jsonObject.get("openid");
        Student s = studentRepository.findByOpenid(openid);
        if (s == null) {
            s = new Student();
            s.setOpenid(openid);
        }
        return s;
    }
}
