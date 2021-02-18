package com.example.Model.Entities;

import lombok.Data;

@Data
public class User {
    private int id;
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String address;
    private int phone;

    public User(int id, String username, String password, String fullname, String email, String address, int phone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone=" + phone +
                '}';
    }
}
