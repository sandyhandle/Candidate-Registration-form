package com.registration.controllers;


import com.registration.model.Candidate;
import com.registration.services.CandidateService;
<<<<<<<< HEAD:src/main/java/com/registration/controllers/Controller.java
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
========
import jakarta.servlet.http.HttpSession;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
>>>>>>>> main:src/main/java/com/registration/controllers/CandidateController.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

<<<<<<<< HEAD:src/main/java/com/registration/controllers/Controller.java
import java.util.List;
@org.springframework.stereotype.Controller

public class Controller {

    @Autowired
    private CandidateService services;

    Logger logger = LoggerFactory.getLogger(Controller.class);

    // For login page

    @GetMapping("/")
    public String loginLoder(){
        return "index";
    }

    @GetMapping("/adminLogin")
    public String adminLoginPageLoder(){
        return "adminLogin";
    }

    @GetMapping("/candidateLogin")
    public String candidateLoginLoder(){
        return "candidateLogin";
    }

    // for candidate

========
@Controller
public class CandidateController {

    Logger logger = LoggerFactory.getLogger(CandidateController.class);

    @Autowired
    private CandidateService services;
>>>>>>>> main:src/main/java/com/registration/controllers/CandidateController.java
    @PostMapping("/candidateData")
    public String candidateRegistrationPageLoder(@ModelAttribute Candidate candidate, Model model){
        logger.info(candidate.toString());
        if (candidate.getCandidateName().equals("")){
            return "newCandidate";
        }
        model.addAttribute("candidateName",candidate.getCandidateName());

        services.save(candidate);
<<<<<<<< HEAD:src/main/java/com/registration/controllers/Controller.java
        logger.info("Candidate saved successfully to the database :) ");
========
       logger.info("Candidate saved successfully to the database :) ");
>>>>>>>> main:src/main/java/com/registration/controllers/CandidateController.java


        return "candidateRegistrationSuccessful";
    }
    @GetMapping("newCandidate")
    public String newCandidatepageLoder(){
        return "newCandidate";
    }


    @PostMapping("/candidateProfile")
    public String candidateProfilePageLoder(@RequestParam("candidateName") String candidateName, @RequestParam("email") String email, Model model, HttpSession session){

        // These and all sensitive datas only logging temporary
        logger.info("Candidate Name: "+ candidateName);
        logger.info("Password: " + email);

        Candidate c = services.getCandidateByNameAndEmail(candidateName, email);
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

    // for Admin page

    @PostMapping("/adminForm")
    public String adminPageLoder(@RequestParam("adminName") String adminName, @RequestParam("password") String password, Model model){
        if (adminName.equals("admin") && password.equals("admin")){
            List<Candidate> candidates = services.getAllCandidates();
            model.addAttribute("entities", candidates);
            return "adminProfile";
        }else {
            return "adminLogin";
        }
    }

    @PostMapping("/adminUpdateForm")
    public String updatingTheApproval(@RequestParam(value = "ID", required = false) Long id, Model model, HttpServletRequest request) {
        if (id != null){
            Candidate c = services.getCandidateById(id);
            if (c != null){
                if (c.getApproved() == true){
                    c.setApproved(false);
                }else {
                    c.setApproved(true);
                }
                services.save(c);
            }
        }
        List<Candidate> candidates = services.getAllCandidates();
        model.addAttribute("entities", candidates);
        return "adminProfile";

    }

}
