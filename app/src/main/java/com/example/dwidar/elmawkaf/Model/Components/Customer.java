package com.example.dwidar.elmawkaf.Model.Components;

public class Customer {

    private String FullName;
    private String PhoneNumber;
    private String email;
    private String UPassword;
    private String CustID;


    public Customer()

    {

    }
    public Customer(String Fn , String email , String phone , String pwd)
    {
        this.FullName = Fn;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUPassword() {
        return UPassword;
    }

    public void setUPassword(String UPassword) {
        this.UPassword = UPassword;
    }

    public String getCustID() {
        return CustID;
    }

    public void setCustID(String custID) {
        CustID = custID;
    }


}
