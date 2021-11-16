package com.example.test.service.Impl;

import com.example.test.dao.InfoMapper;
import com.example.test.dao.PersonMapper;
import com.example.test.dao.CourseMapper;
import com.example.test.models.Course;
import com.example.test.models.MScore;
import com.example.test.models.MStudent;
import com.example.test.service.CourseServiceT;
import com.example.test.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CourseServiceImplT implements CourseServiceT {
    @Autowired
    CourseMapper c;

    @Autowired
    PersonMapper m;

    @Autowired
    InfoMapper infoMapper;

    @Autowired
    ManageImpl manage;


    public boolean judge() {
        if (manage.getChoice() == 1) {
            return DateUtil.isChoose(manage.getStartTime(), manage.getEndTime());
        } else {
            return false;
        }
    }


    @Override
    public void deleteCourse(Integer course_id) {
        c.t_deleteCourse(course_id);
        c.autoFresh(0, course_id);
    }

    @Override
    public List<Course> getMyCour(String token, String semester) {
        return c.t_getMyCourseNow(m.getAccount(token), semester);
    }

    @Override
    public List<Course> getMyCour(String token) {
        return c.t_getMyCourse(m.getAccount(token));
    }


    public void putScore(String student_id, String course_id, int score) {
        Object pop = c.getScore(student_id, course_id);
        if (pop != null) {
            c.updateScore(student_id, course_id, score);
        } else {
            c.t_add_score(student_id, course_id, DateUtil.getSemester(), score);
        }
    }

    public List<MStudent> getStudent(String course_id) {

        List<MStudent> ids = c.getStudent_id(course_id, 1);
        for (int i = 0; i < ids.size(); i++) {
            ids.get(i).name = infoMapper.getName(ids.get(i).id);
            Object score = c.getScore(ids.get(i).id, course_id);
            if (score != null) {
                ids.get(i).score = (Integer) score;
            } else {
                ids.get(i).score = 0;
            }
        }
        return ids;
    }

    public void updateRemark(String remark, int t) {
        c.change(remark, t);
    }

    public List<Integer> getScore(int course_id) {
        return c.itemScore(course_id);
    }

    public boolean judgeRepeat(String token, int week_Start, int week_end, String week, String day) {

        List<Course> prepare = getMyCour(token);
        Course beJudge;
        for (int i = 0; i < prepare.size(); i++) {
            beJudge = prepare.get(i);
            if (beJudge.getTime_class().length() == week.length()) {
                if (beJudge.getWeek_start() <= week_end && beJudge.getWeek_end() >= week_Start) {
                    if (beJudge.getWeek().equals(day) && beJudge.getTime_class().equals(week)) {
                        return false;
                    }
                }
            } else if (beJudge.getWeek_start() <= week_end || beJudge.getWeek_end() >= week_Start) {
                if (beJudge.getTime_class().length() == 1) {
                    if (week.indexOf(beJudge.getTime_class()) != -1 || !beJudge.getWeek().equals(day)) {
                        return false;
                    }
                } else if (week.length() == 1) {
                    if (beJudge.getTime_class().indexOf(week) != -1 || beJudge.getWeek().equals(day)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public List<Integer> cutScore(int courseId) {
        try {
            int h1 = 0, h2 = 0, h3 = 0, h4 = 0, h5 = 0;
            List<Integer> score = getScore(courseId);
            List<Integer> analyze = new ArrayList<>();
            if (score != null) {
                for (int i = 0; i < score.size(); i++) {
                    if (score.get(i) < 60) {
                        h5++;
                    } else if (score.get(i) < 70) {
                        h4++;
                    } else if (score.get(i) < 80) {
                        h3++;
                    } else if (score.get(i) < 90) {
                        h2++;
                    } else if (score.get(i) <= 100) {
                        h1++;
                    }
                }

                analyze.add(h1);
                analyze.add(h2);
                analyze.add(h3);
                analyze.add(h4);
                analyze.add(h5);
                return analyze;
            } else {
                analyze.add(h1);
                analyze.add(h2);
                analyze.add(h3);
                analyze.add(h4);
                analyze.add(h5);
                return analyze;
            }

        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }
}
