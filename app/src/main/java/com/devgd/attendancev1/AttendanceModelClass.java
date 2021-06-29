package com.devgd.attendancev1;

import com.google.firebase.firestore.Exclude;

public class AttendanceModelClass {
    String name;
    String dep;
    String sec;
    String regNo;

    public AttendanceModelClass() {
    }

    public AttendanceModelClass(String name, String dep, String sec) {
        this.name = name;
        this.dep = dep;
        this.sec = sec;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public String getSec() {
        return sec;
    }

    public void setSec(String sec) {
        this.sec = sec;
    }

    @Exclude
    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }
}
