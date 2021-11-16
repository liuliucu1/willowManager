package com.example.test.models;



public class MCourse {
    public int getCourser_id() {
        return courser_id;
    }

    public void setCourser_id(int courser_id) {
        this.courser_id = courser_id;
    }

    private int courser_id;
    private String student_id;
    private String course_name;
    private String semester;
    private int score;

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public MCourse(int courser_id, String student_id, String course_name, String semester, int score) {
        this.courser_id = courser_id;
        this.student_id = student_id;
        this.course_name = course_name;
        this.semester = semester;
        this.score = score;
    }

    public MCourse(int courser_id, String semester, int score) {
        this.courser_id = courser_id;
        this.semester = semester;
        this.score = score;
    }

    public MCourse(String course_name, String semester, int score) {
        this.course_name = course_name;
        this.semester = semester;
        this.score = score;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
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
