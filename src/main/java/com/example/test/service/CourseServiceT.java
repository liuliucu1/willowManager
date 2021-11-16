package com.example.test.service;

import com.example.test.models.Course;
import com.example.test.models.MScore;

import java.util.List;

public interface CourseServiceT {

    void deleteCourse(Integer courseid);

    List<Course> getMyCour(String teacher_id, String semester);

    List<Course> getMyCour(String teacher_id);


}
