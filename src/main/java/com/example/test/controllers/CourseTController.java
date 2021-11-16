package com.example.test.controllers;
import com.example.test.config.Result;
import com.example.test.dao.CourseMapper;
import com.example.test.dao.PersonMapper;
import com.example.test.models.MScore;
import com.example.test.service.Impl.CourseServiceImplT;
import com.example.test.util.DateUtil;
import com.example.test.util.GJson;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin(origins = "http://localhost:8080/", maxAge = 3600)
public class CourseTController {
    @Autowired
    CourseServiceImplT courseServiceImpl;
    @Autowired
    CourseMapper c;
    @Autowired
    PersonMapper personMapper;


    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
    public Result addCourse(@RequestParam("name") String name, @RequestParam("semester") String semester, @RequestParam("fee") String fee,
                          @RequestParam("location") String location, @RequestParam("remark") String remark,
                          @RequestParam("time_start") int week_start, @RequestParam("time_end") int week_end, @RequestParam("kind") String kind,
                          @RequestParam("time_class") String time_class, @RequestParam("week") String week,
                          @RequestParam("token") String token) {

        if (courseServiceImpl.judgeRepeat(token,week_start,week_end,week,time_class)){
            c.t_addCourse(name, semester, fee, location, remark, week_start, week_end, kind, time_class, week, personMapper.getAccount(token));
            return Result.success("成功");
        }else{
            return Result.fail("冲突");
        }


    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteCour(@Param("course_id") String course_id) {
        courseServiceImpl.deleteCourse(Integer.parseInt(course_id));
    }

    @RequestMapping(value = "/getMyC", method = RequestMethod.POST)
    public String getMy(@Param("token") String token) {
        return GJson.toJson(courseServiceImpl.getMyCour(token, DateUtil.getSemester()));
    }
    @RequestMapping(value = "/getCourse", method = RequestMethod.POST)
    public String getMyC(@Param("token") String token) {
        return GJson.toJson(courseServiceImpl.getMyCour(token));
    }


    //这里等前端可以读取excel成功了就把他转化成这个对象，然后进行处理
    @RequestMapping(value = "/putScore", method = RequestMethod.POST)
    public void putScore(@RequestParam("score") int score,@RequestParam("courseId")String courseId,@RequestParam("account")String account) {
        courseServiceImpl.putScore(account,courseId,score);
    }

    @RequestMapping(value = "/studentName", method = RequestMethod.GET)
    public Result getStudent(@RequestParam("courseid") String courseid) {
        if(courseServiceImpl.judge()){
            return Result.fail("选课还未截止");
        }
        return Result.success(courseServiceImpl.getStudent(courseid));
    }

    @RequestMapping(value = "/changeRemark", method = RequestMethod.POST)
    public void change(@RequestParam("remark") String remark, @RequestParam("course_id") String course_id) {
        courseServiceImpl.updateRemark(remark, Integer.parseInt(course_id));
    }

   @RequestMapping(value = "/studentMark", method = RequestMethod.GET)
   public String getScore(@RequestParam("courseId")int courseId){
        return GJson.toJson(courseServiceImpl.getScore(courseId));
   }


   @RequestMapping(value = "/analyzeScore",method = RequestMethod.GET)
    public String analyze(@RequestParam("courseId")int courseId){
        return GJson.toJson(courseServiceImpl.cutScore(courseId));
   }
}
