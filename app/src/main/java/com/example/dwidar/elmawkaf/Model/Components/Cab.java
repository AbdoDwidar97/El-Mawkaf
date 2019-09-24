package com.example.dwidar.elmawkaf.Model.Components;

public class Cab {

    private String FullName;
    private String PhoneNumber;
    private String EmpID;
    private String UPassword;
    private String CabID;

    // AIzaSyB0o2P1IGzbp0_p-aqM4v0nl_jHLJZTU3I
    public Cab()
    {

    }

    public Cab(String Fn , String empID , String phone , String pwd)
    {
        this.FullName = Fn;
        this.EmpID = empID;
        this.PhoneNumber = phone;
        this.UPassword = pwd;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmpID() {
        return EmpID;
    }

    public void setEmpID(String empID) {
        EmpID = empID;
    }

    public String getUPassword() {
        return UPassword;
    }

    public void setUPassword(String UPassword) {
        this.UPassword = UPassword;
    }

    public String getCabID() {
        return CabID;
    }

    public void setCabID(String cabID) {
        CabID = cabID;
    }


}
