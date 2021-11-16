package com.example.test.models;

public class MStudent {
    public String id;
    public String name;
    public int score;
    public MStudent(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public MStudent(String id) {
        this.id = id;
    }

    public MStudent(String id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }
}
