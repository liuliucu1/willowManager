package com.example.test.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "info")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String idcard;

    @NotBlank
    @Size(max = 12)
    private String phone;

    @NotBlank
    @Size(max = 10)
    private String name;

    @Size(max =20)
    private String account;
    @NotBlank
    private String mail;
    @NotBlank
    private String address;

    public User() {

    }
    public int age;



    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User(String idcard, @NotBlank @Size(max = 12) String phone, @NotBlank @Size(max = 10) String name, @Size(max = 20) String account, @NotBlank String mail, @NotBlank String address) {
        this.idcard = idcard;
        this.phone = phone;
        this.name = name;
        this.account = account;
        this.mail = mail;
        this.address = address;
    }

    public User(String idcard, @NotBlank @Size(max = 12) String phone, @NotBlank @Size(max = 10) String name, @Size(max = 20) String account, int age) {
        this.idcard = idcard;
        this.phone = phone;
        this.name = name;
        this.account = account;
        this.age = age;
    }

    public User(String idcard, @NotBlank @Size(max = 12) String phone, @NotBlank @Size(max = 10) String name, @Size(max = 20) String account, @NotBlank String mail, @NotBlank String address, int age) {
        this.idcard = idcard;
        this.phone = phone;
        this.name = name;
        this.account = account;
        this.mail = mail;
        this.address = address;
        this.age = age;
    }
}
