package com.stusocial.springboot.controller;

import com.stusocial.springboot.entity.Community;
import com.stusocial.springboot.entity.StudentBelongCommunity;
import com.stusocial.springboot.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/stusocial/community")
public class CommunityController {
    @Autowired
    private CommunityService communityService;

    @RequestMapping("/add")
    public Community addCommunity(@RequestParam(value = "studentid") Integer sid, @RequestParam(value = "imgCount") Integer imgCount, @RequestParam(value = "university") String university, @RequestParam(value = "name") String name, @RequestParam(value = "content") String content, @RequestParam(value = "category") String category) {
        return communityService.addCommunity(sid, university, name, content, category, imgCount);
    }

    @RequestMapping("/uploadfile")
    public String uploadImage(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "communityid") Integer comId, @RequestParam(value = "count") Integer count) {
        if (communityService.uploadImage(file, comId, count)) {
            return "success";
        }
        return "fail";
    }

    @RequestMapping(value = "/getImage/{filename}", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody  //filename就是社区id
    public byte[] getImage(@PathVariable(value = "filename") String fileName) throws IOException {
        return communityService.getImage(fileName);
    }

    @RequestMapping(value = "/byUniversity")
    @ResponseBody
    public List<StudentBelongCommunity> getCommunitiesByUni(@RequestParam(value = "university") String university, @RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size) {
        return communityService.getCommunitiesByUniOrCat(university, page, size, "university");

    }

    @RequestMapping(value = "/byCategory")
    @ResponseBody
    public List<StudentBelongCommunity> getCommunitiesByCategory(@RequestParam(value = "category") String category, @RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size) {
        return communityService.getCommunitiesByUniOrCat(category, page, size, "category");
    }

    @RequestMapping(value = "/byId")
    @ResponseBody
    public Community getCommunityById(@RequestParam(value = "id") Integer id) {
        Community com = communityService.getCommunityById(id);
        return com;
    }

    @DeleteMapping("/{id}")
    public String deleteCommunityById(@PathVariable("id") Integer id) {
        communityService.deleteCommunityById(id);
        return "success";
    }
}
