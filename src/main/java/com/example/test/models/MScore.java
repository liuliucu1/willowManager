package com.example.test.models;

public class MScore {
    public String student_id;
    public String course_id;

    public String semester;
    public int score;

    public MScore(String student_id, String course_id, String semester, int score) {
        this.student_id = student_id;
        this.course_id = course_id;
        this.semester = semester;
        this.score = score;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
