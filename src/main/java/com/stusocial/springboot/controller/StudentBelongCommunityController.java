package com.stusocial.springboot.controller;

import com.stusocial.springboot.entity.Student;
import com.stusocial.springboot.entity.StudentBelongCommunity;
import com.stusocial.springboot.service.StudentBelongCommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stusocial/stuBelongCom")
public class StudentBelongCommunityController {
    @Autowired
    private StudentBelongCommunityService studentBelongCommunityService;

    @RequestMapping(value = "/byComAndStu")
    public StudentBelongCommunity getStuBeComByComAndStu(@RequestParam(value = "studentid") Integer studentid, @RequestParam(value = "communityid") Integer communityid) {
        return studentBelongCommunityService.getStuBelongCom(studentid, communityid);
    }

    @PostMapping(params = {"sid", "cid"})
    public String addStuBelongCom(@RequestParam("sid") Integer sid, @RequestParam("cid") Integer cid) {
        return studentBelongCommunityService.addStuBelongCom(sid, cid);
    }

    @DeleteMapping(params = {"sid", "cid"})
    public Integer deleteStuBelongCom(@RequestParam("sid") Integer sid, @RequestParam("cid") Integer cid) {
        return studentBelongCommunityService.deleteStuBelongCom(sid, cid);
    }

    @GetMapping(params = {"cid"})
    public List<StudentBelongCommunity> getStuBelongComsByCom(@RequestParam("cid") Integer cid) {
        return studentBelongCommunityService.getStuBelongComsByCom(cid);
    }

    @GetMapping(params = {"sid", "page", "pagesize", "privilege"})
    public List<StudentBelongCommunity> getStuBelongComsByStu(@RequestParam("sid") Integer sid, @RequestParam("page") Integer page, @RequestParam("pagesize") Integer pagesize, @RequestParam("privilege") Integer privilege) {
        return studentBelongCommunityService.getStuBelongComsByStu(sid, page, pagesize, privilege);
    }
}
