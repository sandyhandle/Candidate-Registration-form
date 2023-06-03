package com.registration.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "candidate")
@ToString
@NoArgsConstructor
@Getter
@Setter
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int paidAmount = 0;

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

    public String getCity() {
        return city;
    }

    @Column(name = "approved")
    private Boolean approved = false;

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
    public void setPaidAmount(int paidAmount) {
        this.paidAmount += paidAmount;
    }
}