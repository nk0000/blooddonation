package com.example.blooddonation;

public class details {
    String name;
    String phone;
    String bloodGroup;
    String medicalIssues;
    String city;
    details(){
        name="Donor";
        phone="phone";
        bloodGroup="Blood Group";
        medicalIssues="NIL";
        city="City";
    }
    public details(String name,String phone,String bloodGroup,String city, String medicalIssues){
        this.name=name;
        this.phone=phone;
        this.bloodGroup=bloodGroup;
        this.city=city;
        this.medicalIssues=medicalIssues;
    }
    public String getName(){
        return name;
    }
    public String getPhone(){
        return phone;
    }
    public String getBloodGroup(){
        return bloodGroup;
    }
    public String getCity(){return city;}
    public String getMedicalIssues(){
        return medicalIssues;
    }
}
