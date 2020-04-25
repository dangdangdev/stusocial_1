package com.stusocial.springboot.service;

import com.stusocial.springboot.entity.Community;
import com.stusocial.springboot.entity.Student;
import com.stusocial.springboot.entity.StudentBelongCommunity;
import com.stusocial.springboot.repository.CommunityRepository;
import com.stusocial.springboot.repository.StudentBelongCommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional(readOnly = false, rollbackFor = Throwable.class)
public class CommunityService {
    private String imagePathWin = "D:/FILE/ProjectFile/stusocial/communityimage/";
    private String imagePathUnix = "/home/dang/FILE/stusocial/communityimage/";
    @Autowired
    private CommunityRepository communityRepository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentBelongCommunityRepository stuBCR;


    public Community addCommunity(Integer id, String unversity, String name, String content, String category, Integer imgCount) {
        Student s = studentService.getStudentById(id);
        Community community = new Community(category, unversity, name, content, imgCount);
        Community com = communityRepository.save(community);
        StudentBelongCommunity sbc = new StudentBelongCommunity(s, com, 0);
        stuBCR.save(sbc);
        return com;
    }


    public Community getCommunityById(Integer id) {
        return communityRepository.findById(id).get();
    }

    public boolean uploadImage(MultipartFile file, Integer comId, Integer count) {
        String path = this.imagePathWin + String.valueOf(comId) + "/" + String.valueOf(count) + ".png";
        File f = new File(path);
        f.mkdirs();
        try {
            file.transferTo(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public byte[] getImage(String fileName) {
        try {
            String[] path = fileName.split("-");
            String filePath = this.imagePathWin + path[0] + "/" + path[1];
            File file = new File(filePath);
            FileInputStream inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());
            inputStream.close();
            return bytes;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<StudentBelongCommunity> getCommunitiesByUniOrCat(String data, Integer page, Integer size, String method) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        List<Community> cs = null;
        List<StudentBelongCommunity> sbcs = new ArrayList<>();
        if (method.equals("university")) {
            cs = communityRepository.findCommunitiesByUnversityOrderById(data, pageable);
        } else if (method.equals("category")) {
            cs = communityRepository.findCommunitiesByCategoryOrderById(data, pageable);
        }
        if (cs != null) {
            for (Community c : cs) {
                List<StudentBelongCommunity> sbcsP = stuBCR.findByCommunityAndPrivilege(c, 0);
                if (sbcsP != null) {
                    sbcs.add(sbcsP.get(0));
                }
            }
        }
        return sbcs;
    }

    public void deleteCommunityById(Integer id) {
        communityRepository.deleteById(id);
    }

}
