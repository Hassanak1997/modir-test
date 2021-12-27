package com.example.modir;

public class User {
    private String fullname;
    private String phone;

    public String getName() {
        return fullname;
    }

    public void setName(String name) {
        this.fullname = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountrest() {
        return countrest;
    }

    public void setCountrest(String countrest) {
        this.countrest = countrest;
    }

    private String countrest;
}
