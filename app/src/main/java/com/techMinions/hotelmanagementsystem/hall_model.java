package com.techMinions.hotelmanagementsystem;

public class hall_model {

    private String numpeople;
    private String hallType;
    private String date;
    private String time;
    private String fullName;
    private String email;
    private String phone;

    public hall_model() {

    }

    public void setNumpeople(String numpeople) { this.numpeople = numpeople;}

    public void setHallType(String hallType) {
        this.hallType = hallType;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNumpeople() {
        return numpeople;
    }

    public String getHallType() {
        return hallType;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
