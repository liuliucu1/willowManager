package com.example.test.models;

import javax.persistence.*;


@Entity

public class Course {

    @Id
    private int course_id;

    private String name;

private int isChoose;
    private String teacher_id;


    private String kind;//课的种类

    private String week;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
    public Course(String name, String kind,String week, String fee, String time_class, int week_start, int week_end, String location, int now, String remark, String semester) {

        this.name = name;
        this.kind = kind;
        this.week=week;
        this.fee = fee;
        this.time_class = time_class;
        this.week_start = week_start;
        this.week_end = week_end;
        this.location = location;
        this.now = now;
        this.remark = remark;
        this.semester = semester;
    }


    private String teacherName;


    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    private String fee;

    public Course(int course_id, String name, String teacher_id, String kind, String week, String fee, String time_class, int week_start, int week_end, String location, int now, String remark, String semester) {
        this.course_id = course_id;
        this.name = name;
        this.teacher_id = teacher_id;
        this.kind = kind;
        this.week = week;
        this.fee = fee;
        this.time_class = time_class;
        this.week_start = week_start;
        this.week_end = week_end;
        this.location = location;
        this.now = now;
        this.remark = remark;
        this.semester = semester;
    }

    public Course(int course_id, String name, String teacher_id, String kind, String week, String teacherName, String fee, String time_class, int week_start, int week_end, String location, int now, String remark, String semester) {
        this.course_id = course_id;
        this.name = name;
        this.teacher_id = teacher_id;
        this.kind = kind;
        this.week = week;
        this.teacherName = teacherName;
        this.fee = fee;
        this.time_class = time_class;
        this.week_start = week_start;
        this.week_end = week_end;
        this.location = location;
        this.now = now;
        this.remark = remark;
        this.semester = semester;
    }

    public String getTime_class() {
        return time_class;
    }

    public void setTime_class(String time_class) {
        this.time_class = time_class;
    }

    private String time_class;

    private int week_start;

    private int week_end;


    private String location;

    private int now;


    private String remark;


    private String semester;


    public Course() {

    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }


    public int getWeek_start() {
        return week_start;
    }

    public void setWeek_start(int week_start) {
        this.week_start = week_start;
    }

    public int getWeek_end() {
        return week_end;
    }

    public void setWeek_end(int week_end) {
        this.week_end = week_end;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNow() {
        return now;
    }

    public void setNow(int now) {
        this.now = now;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public int getIsChoose() {
        return isChoose;
    }

    public void setIsChoose(int isChoose) {
        this.isChoose = isChoose;
    }

    public Course(int course_id, String name, int isChoose, String teacher_id, String kind, String week, String teacherName, String fee, String time_class, int week_start, int week_end, String location, int now, String remark, String semester) {
        this.course_id = course_id;
        this.name = name;
        this.isChoose = isChoose;
        this.teacher_id = teacher_id;
        this.kind = kind;
        this.week = week;
        this.teacherName = teacherName;
        this.fee = fee;
        this.time_class = time_class;
        this.week_start = week_start;
        this.week_end = week_end;
        this.location = location;
        this.now = now;
        this.remark = remark;
        this.semester = semester;
    }
}
