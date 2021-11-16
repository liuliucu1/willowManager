package com.example.test.dao;

import com.example.test.models.Course;
import com.example.test.models.MCourse;
import com.example.test.models.MStudent;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface CourseMapper {

    //已经选过的课，和已经选过的时间段不可以重复选
    @Insert("insert into course(name,teacher_id,kind,time_class,week_start,week_end,location,remark,semester,week,fee) values(#{name},#{teacher_id},#{kind},#{time_class},#{week_start},#{week_end},#{location},#{remark},#{semester},#{week},#{fee})")
    void t_addCourse(@Param("name") String name, @Param("semester") String semester, @Param("fee") String fee,
                     @Param("location") String location, @Param("remark") String remark,
                     @Param("week_start") int week_start, @Param("week_end") int week_end, @Param("kind")String kind,
                     @Param("week") String week, @Param("time_class")String time_class,
                     @Param("teacher_id") String teacher_id);//记得把remark默认成空；

    @Update("UPDATE `course` SET remark = #{remark} WHERE course_id = #{course_id}")
    void change(@Param("remark") String remark,@Param("course_id") int course_id);
    //    String name,String teacher_id,String kind,String time_start,String time_end,String week_start,String week_end,String location,String now,String remark
    //老师删课
    @Delete("delete from course where course_id = #{course_id}")
    void t_deleteCourse(int course_id);

    @Update("UPDATE `table` SET isChoose = #{isChoose} WHERE course_id = #{course_id}")
    void autoFresh(int isChoose,  int course_id);

    //老师录入成绩
    @Insert("insert into find_score(student_id,course_id,semester,score) values(#{student_id},#{course_id},#{semester},#{score})")
    void t_add_score(String student_id, String course_id, String semester, int score);

    @Update("update `find_score` set score = #{score} where student_id = #{student_id} and course_id = #{course_id}")
    void updateScore(@Param("student_id")String student_id,@Param("course_id") String course_id,@Param("score") int score);

    @Select("select score from `find_score` where student_id = #{student_id} and course_id = #{course_id}")
    Integer getScore(@Param("student_id")String student_id, @Param("course_id")String course_id );

    @Select("Select * from course where teacher_id = #{teacher_id}")
    List<Course> t_getMyCourse(@Param("teacher_id") String teacher_id);

    @Select("Select * from course where teacher_id = #{teacher_id} and semester = #{semester}")
    List<Course> t_getMyCourseNow(@Param("teacher_id") String teacher_id, @Param("semester") String semester);

    //根据学期，返回课程 把时间学期调回来之后，就不用一直调用数据库的信息了where semester = #{semester}
    @Select("Select * from course where semester = #{semester}")
    List<Course> getAllCourse(String semester);


    @Select("Select course_id from `table` where semester = #{semester} and student_id = #{student_id} and isChoose = #{isChoose}")
    List<Integer> getChosenC(String semester, String student_id, int isChoose);

    @Select("Select * from `course` where course_id = #{course_id}")
    Course getCourse(int course_id);

    //选课
    @Insert("Insert into `table`(student_id,course_id,isChoose,semester) values(#{student_id},#{course_id},#{isChoose},#{semester})")
    void chooseCourse(String student_id, int course_id, int isChoose, String semester);

    @Select("select semester from `table` where student_id=#{student_id} and course_id=#{course_id}")
    String flag(@Param("student_id") String student_id, @Param("course_id") int course_id);

    //退课
    @Update("UPDATE `table` SET isChoose = #{isChoose} WHERE student_id = #{student_id} and course_id = #{course_id}")
    void fresh(int isChoose, String student_id, int course_id);

    //学生返回这个学期所有的成绩（）
    @Select("select * from find_score where student_id = #{student_id} and semester = #{semester}")
    List<MCourse> S_scores(String student_id, String semester);

    @Select("select name from course where course_id = #{course_id}")
    String courseName(@Param("course_id") int course_id);

    //返回选课名单
    @Select("select student_id from `table` where course_id = #{course_id} and isChoose = #{isChoose}")
    List<MStudent> getStudent_id(@Param("course_id") String course_id, @Param("isChoose") int isChoose);

    @Select("select isChoose from `table` where course_id = #{course_id} and student_id = #{student_id}")
    Object flagChoose(@Param("course_id") int course_id,@Param("student_id")String student_id);


    @Select("select score from find_score where course_id = #{course_id}")
    List<Integer> itemScore(@Param("course_id")int course_id);


}
