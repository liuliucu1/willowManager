package com.example.test.controllers;

import com.alibaba.fastjson.JSON;
import com.example.test.config.Result;

import com.example.test.models.MCourse;
import com.example.test.service.Impl.CourseServiceImplS;

import com.example.test.service.Impl.ManageImpl;
import com.example.test.util.DateUtil;
import com.example.test.util.GJson;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080/", maxAge = 3600)

public class CourseSController {
    @Autowired
    CourseServiceImplS courseServiceImpl;

    @RequestMapping(value = "/getAllCourse",method=RequestMethod.POST)
    public String getAll(@RequestParam("token") String token){
        if (courseServiceImpl.judge()){
            return GJson.toJson(courseServiceImpl.getAll(token,DateUtil.getSemester()));
        }else{
            return "-1";
        }

    }

    @RequestMapping(value = "/chooseCourse",method = RequestMethod.POST)
    public Result chooseCourse(@RequestParam("course_id") String courseId,@RequestParam("token") String token){

        if (courseServiceImpl.S_Choose(Integer.parseInt(courseId),token)==0){
            return Result.success("修改成功");
        }else{
            return Result.fail("选课冲突");
        }
    }

    @RequestMapping(value = "/getCourseC",method = RequestMethod.POST)
    public Result getTable(@RequestParam("token")String token){
        //查找当前课表
        return Result.success(courseServiceImpl.getNowCour(DateUtil.getSemester(),token));
    }

    @RequestMapping(value = "/quit",method = RequestMethod.POST)
    public void quitCourse (@RequestParam("course_id")String course_id,@RequestParam("token")String token){
        courseServiceImpl.S_drop(Integer.parseInt(course_id),token);
    }

    @RequestMapping(value = "/lookScore",method = RequestMethod.POST)
    public List<MCourse> getScore(@RequestParam("token")String token, @RequestParam("semester") String semester){
        System.out.println(JSON.toJSONString(courseServiceImpl.getScore(token, semester)));
        return courseServiceImpl.getScore(token, semester);
    }

}
