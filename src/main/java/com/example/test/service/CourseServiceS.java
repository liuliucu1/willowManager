package com.example.test.service;

import com.example.test.models.Course;
import com.example.test.models.MCourse;

import java.util.List;


public interface CourseServiceS {
    List<MCourse> getScore(String token);
    List<MCourse> getScore(String token, String semester);
    List<Course> getNowCour(String semester, String token);
    int S_Choose(int course_id, String token);
    void S_drop(List<Integer> course_id,String token);
}
