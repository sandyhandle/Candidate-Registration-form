package com.registration.controller;

import com.registration.model.Candidate;
import com.registration.services.RepositoryServices;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CandidateControler {

    @Autowired
    private RepositoryServices services;
    @PostMapping("/candidateData")
    public String candidateRegistrationPageLoder(@ModelAttribute Candidate candidate, Model model){
        System.out.println(candidate.toString());
        if (candidate.getCandidateName().equals("")){
            return "newCandidate";
        }
        model.addAttribute("candidateName",candidate.getCandidateName());

        services.save(candidate);
        System.out.println("Candidate saved successfully to the database :) ");


        return "candidateRegistrationSuccessful";
    }

    @GetMapping("/candidateData")
    public String candidateRegistrationEmptyCall(){
        return "candidateLogin";
    }
    @PostMapping("/newCandidate")
    public String newCandidateCall(){
        return "newCandidate";
    }

    @GetMapping("/newCandidate")
    public String newCandidateDirectCall(){
        return "newCandidate";
    }

    @PostMapping("/candidateProfile")
    public String candidateProfilePageLoder(@RequestParam("candidateName") String candidateName, @RequestParam("email") String email, Model model, HttpSession session){

        System.out.println("Candidate Name: "+ candidateName);
        System.out.println("Password: " + email);

        Candidate c = services.getCandidate(candidateName, email);
        if (c != null){
            session.setAttribute("candidate", c);
            String approve;
            if (c.getApproved()){
                approve = "Approved";
            } else {
                approve = "Not Approved";
            }
            model.addAttribute("id",c.getId());
            model.addAttribute("candidateName", c.getCandidateName());
            model.addAttribute("email", c.getEmail());
            model.addAttribute("address", c.getAddress());
            model.addAttribute("city",c.getCity());
            model.addAttribute("state", c.getState());
            model.addAttribute("phoneNumber", c.getPhoneNumber());
            model.addAttribute("qualification",c.getQualification());
            model.addAttribute("gender",c.getGender());
            model.addAttribute("skills", c.getSkills());
            model.addAttribute("approved",approve);
            model.addAttribute("paidAmount",c.getPaidAmount());

            return "candidateProfile";
        }
        return "candidateLogin";
    }
    @GetMapping("/candidateProfile")
    public String candidateProfileEmptyCall(){
        return "candidateLogin";
    }

    @GetMapping("/candidateLogin")
    public String candidateLogin(){
        return "candidateLogin";
    }
    @PostMapping("/paymentPage")
    public String handlePayment(@RequestParam("paymentAmount") int paymentAmount,  Model model, HttpSession session) {
        // Get the candidate object based on the session
        Candidate c = (Candidate) session.getAttribute("candidate");

        // Update the candidate object with the payment information
        c.setPaidAmount(paymentAmount);
        // You can also update other relevant fields if needed

        // Save the updated candidate object
        services.save(c);
        String approve;
        if (c.getApproved()){
            approve = "Approved";
        } else {
            approve = "Not Approved";
        }

        // Redirect to the candidate profile page or any other appropriate page\
        model.addAttribute("id",c.getId());
        model.addAttribute("candidateName", c.getCandidateName());
        model.addAttribute("email", c.getEmail());
        model.addAttribute("address", c.getAddress());
        model.addAttribute("city",c.getCity());
        model.addAttribute("state", c.getState());
        model.addAttribute("phoneNumber", c.getPhoneNumber());
        model.addAttribute("qualification",c.getQualification());
        model.addAttribute("gender",c.getGender());
        model.addAttribute("skills", c.getSkills());
        model.addAttribute("approved",approve);
        model.addAttribute("paidAmount",c.getPaidAmount());
        return "candidateProfile";
    }
    @GetMapping("paymentPage")
    public String paymentPageLoder(Model model, HttpSession session){
        Candidate c = (Candidate) session.getAttribute("candidate");
        if (c.getApproved()) {
            return "payment";
        } else{
            String approve;
            if (c.getApproved()){
                approve = "Approved";
            } else {
                approve = "Not Approved";
            }

            // Redirect to the candidate profile page or any other appropriate page\
            model.addAttribute("id",c.getId());
            model.addAttribute("candidateName", c.getCandidateName());
            model.addAttribute("email", c.getEmail());
            model.addAttribute("address", c.getAddress());
            model.addAttribute("city",c.getCity());
            model.addAttribute("state", c.getState());
            model.addAttribute("phoneNumber", c.getPhoneNumber());
            model.addAttribute("qualification",c.getQualification());
            model.addAttribute("gender",c.getGender());
            model.addAttribute("skills", c.getSkills());
            model.addAttribute("approved",approve);
            model.addAttribute("paidAmount",c.getPaidAmount());
            return "candidateProfile";
        }


    }

}
