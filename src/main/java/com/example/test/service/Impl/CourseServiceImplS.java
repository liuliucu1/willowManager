package com.example.test.service.Impl;

import com.example.test.dao.InfoMapper;
import com.example.test.dao.PersonMapper;
import com.example.test.dao.CourseMapper;
import com.example.test.models.Course;
import com.example.test.models.MCourse;
import com.example.test.service.CourseServiceS;
import com.example.test.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImplS implements CourseServiceS {
    @Autowired
    CourseMapper c;
    @Autowired
    PersonMapper m;
    @Autowired
    InfoMapper infoMapper;
    @Autowired
    ManageImpl manage;

    @Override
    public List<MCourse> getScore(String token) {
        return c.S_scores(m.getAccount(token), DateUtil.getSemester());
    }

    public boolean judge() {
        if (manage.getChoice() == 1) {
            return DateUtil.isChoose(manage.getStartTime(), manage.getEndTime());
        } else {
            return false;
        }
    }


    @Override
    public List<MCourse> getScore(String token, String semester) {
        List<MCourse> a = c.S_scores(m.getAccount(token), semester);
        for (int i = 0; i < a.size(); i++) {
            String h = c.courseName(a.get(i).getCourser_id());
            a.get(i).setCourse_name(h);
        }
        return a;
    }

    @Override
    public List<Course> getNowCour(String semester, String token) {
        List<Course> result = new ArrayList<>();
        List<Integer> id = c.getChosenC(semester, m.getAccount(token), 1);
        for (int i : id) {
            result.add(c.getCourse(i));
        }
        return result;
    }

    @Override
    public int S_Choose(int course_id, String token) {
        List<Course> prepare = getNowCour(DateUtil.getSemester(), token);
        Course beJudge;
        Course judge = c.getCourse(course_id);
        for (int i = 0; i < prepare.size(); i++) {
            beJudge = prepare.get(i);
            if (beJudge.getTime_class().length() == judge.getTime_class().length()) {
                if (beJudge.getWeek_start() <= judge.getWeek_end() || beJudge.getWeek_end() >= judge.getWeek_start()) {
                    if (beJudge.getWeek().equals(judge.getWeek()) && beJudge.getTime_class().equals(judge.getTime_class())) {
                        return 1;
                    }
                }
            } else if (beJudge.getWeek_start() <= judge.getWeek_end() || beJudge.getWeek_end() >= judge.getWeek_start()) {
                if (beJudge.getTime_class().length() == 1) {
                    if (judge.getTime_class().contains(beJudge.getTime_class()) || beJudge.getWeek().equals(judge.getWeek())) {
                        return 1;
                    }
                } else if (judge.getTime_class().length() == 1) {
                    if (beJudge.getTime_class().contains(judge.getTime_class()) || beJudge.getWeek().equals(judge.getWeek())) {
                        return 1;
                    }
                }
            }
        }
        if (c.flag(m.getAccount(token), course_id) == null) {
            c.chooseCourse(m.getAccount(token), course_id, 1, DateUtil.getSemester());
            return 0;
        } else {
            c.fresh(1, m.getAccount(token), course_id);
            return 0;
        }

    }

    @Override
    public void S_drop(List<Integer> course_id, String token) {
        for (int id : course_id) {
            c.fresh(0, m.getAccount(token), id);
        }
    }

    public void S_drop(int course_id, String token) {

        c.fresh(0, m.getAccount(token), course_id);

    }

    public List<Course> getAll(String token, String semester) {
        List<Course> h = c.getAllCourse(semester);
        for (int i = 0; i < h.size(); i++) {
            Course t = h.get(i);
            h.get(i).setTeacherName(infoMapper.getName(t.getTeacher_id()));
            Object hk = c.flagChoose(t.getCourse_id(), m.getAccount(token));
            if (hk != null) {
                h.get(i).setIsChoose((Integer) hk);
            }

        }
        return h;
    }


}
