package com.example.test.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "vertify")
public class Vertify {
    @Id
    private String id;

    @NotBlank
    @Size(max = 45)
    private String password;
    @NotBlank
    @Size(max =20)
    private String account;
    @NotBlank
    @Size(max = 1)
    private Integer identity;

    public Vertify(String id, @NotBlank @Size(max = 45) String password, @NotBlank @Size(max = 20) String account, @NotBlank @Size(max = 1) Integer identity) {
        this.id = id;
        this.password = password;
        this.account = account;
        this.identity = identity;
    }

    public Vertify() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }
}
