package com.registration.model;

import jakarta.persistence.*;

@Entity
@Table(name = "candidate")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    private int paidAmount = 0;

    public int getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(int paidAmount) {
        this.paidAmount += paidAmount;
    }

    @Column(name = "candidate_name")
    private String candidateName;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "qualification")
    private String qualification;
    @Column(name = "gender")
    private String gender;
    @Column(name = "skills")
    private String skills;
    @Column(name = "approved")
    private Boolean approved = false;

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
//    private resume

    @Override
    public String toString() {
        return "Candidate{" +
                "candidateName='" + candidateName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", qualification='" + qualification + '\'' +
                ", gender='" + gender + '\'' +
                ", skills='" + skills + '\'' +
                '}';
    }

    public Candidate() {
    }

    public Candidate(String candidateName, String email, String address, String city, String state, String phoneNumber, String qualification, String gender, String skills) {
        this.candidateName = candidateName;
        this.email = email;
        this.address = address;
        this.city = city;
        this.state = state;
        this.phoneNumber = phoneNumber;
        this.qualification = qualification;
        this.gender = gender;
        this.skills = skills;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}