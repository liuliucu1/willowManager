package com.example.test.models;




public class MUser {
    int identify;
    String token;

    public MUser(int identify, String token) {
        this.identify = identify;
        this.token = token;
    }

    public void setIdentify(int identify) {
        this.identify = identify;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getIdentify() {
        return identify;
    }

    public String getToken() {
        return token;
    }

    public MUser() {
    }


}
