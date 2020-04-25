package com.stusocial.springboot.controller;

import com.stusocial.springboot.entity.Student;
import com.stusocial.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.RequestWrapper;

@RestController
@RequestMapping(value = "/stusocial/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping("/getbyname")
    public Student getStudent(@RequestParam(value = "name") String name) {
        return studentService.getStudentByName(name);
    }

    @RequestMapping("/update")
    public Student updateStudent(@RequestParam(value = "id", required = false) Integer id, @RequestParam(value = "university") String unversity) {
        return studentService.update(id, unversity);
    }

    @RequestMapping("/register")
    public Student studentRegister(@RequestBody Student s) {
        return studentService.register(s);
    }

    @RequestMapping("/login")
    public Student studentLogin(@RequestParam(value = "code") String code) {
        return studentService.login(code);
    }
}
