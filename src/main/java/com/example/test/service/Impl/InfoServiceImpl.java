package com.example.test.service.Impl;

import com.example.test.config.Result;
import com.example.test.dao.InfoMapper;
import com.example.test.dao.PersonMapper;
import com.example.test.models.User;
import com.example.test.service.InfoService;
import com.example.test.util.IdcardUtil;
import com.example.test.util.InfoCorr;
import com.example.test.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class InfoServiceImpl implements InfoService {

    @Autowired
    InfoMapper infoMapper;
    @Autowired
    PersonMapper personMapper;


    public void addPerson(String idCard,String phone,String password,String mail,String address,String name) {
    }

    @Override
    public void updatePersonT() {

    }

    @Override
    public boolean updatePhone(String phone, String token) {
        boolean flag = InfoCorr.isMobileNum(phone);
        if (flag) {
            infoMapper.updatePhone(phone, personMapper.getAccount(token));
            return true;
        } else {
            return false;
        }

    }

    public boolean updateMail(String mail, String token) {
        boolean f = MailUtil.isEmail(mail);
        if (f) {
            infoMapper.updateMail(mail, personMapper.getAccount(token));
            return true;
        } else {
            return false;
        }
    }

    public boolean updateAddress(String address, String token) {
        infoMapper.updateAddress(address,personMapper.getAccount(token));
        return true;
    }

    @Override
    public int addCourse() {
        return 0;
    }

    @Override
    public int deleteCourse() {
        return 0;
    }

    @Override
    public User getUser(String token) {
        User user = infoMapper.getUser(personMapper.getAccount(token));
        int age = IdcardUtil.getAgeByIdCard(user.getIdcard());
        String province = IdcardUtil.getProvinceByIdCard(user.getIdcard());
        user.age = age;
        return user;
    }
}
