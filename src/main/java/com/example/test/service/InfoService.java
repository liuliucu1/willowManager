package com.example.test.service;

import com.example.test.models.User;

public interface InfoService {

    void updatePersonT();
    boolean updatePhone(String phone, String token);
    int addCourse();
    int deleteCourse();
    User getUser(String token);
}
