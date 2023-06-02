package com.registration.controller;

import com.registration.model.Admin;
import com.registration.model.Candidate;
import com.registration.services.CandidateService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private CandidateService services;



    @PostMapping("/adminForm")
    public String adminPageLoder(@RequestParam("adminName") String adminName, @RequestParam("password") String password, Model model){
        if (adminName.equals("admin") && password.equals("admin")){
            Admin.setAdmin(true);
            List<Candidate> candidates = services.getAllCandidate();
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
        List<Candidate> candidates = services.getAllCandidate();
        model.addAttribute("entities", candidates);
        return "adminProfile";

    }

    @GetMapping("/adminForm")
    public String adminEmptyloder(Model model){

        return adminLoginEmpty(model);

    }

    @GetMapping("/adminUpdateForm")
    public String adminUpdateFormloder(Model model){

        return adminLoginEmpty(model);

    }

    @GetMapping("/adminLogin")
    public String adminLoginEmpty(Model model){
        if (Admin.isAdmin()){
            List<Candidate> candidates = services.getAllCandidate();
            model.addAttribute("entities", candidates);
            return "adminProfile";
        }else{
            return "adminLogin";
        }
    }

    @PostMapping("/adminLogout")
    public String adminLogout(){
        Admin.setAdmin(false);
        return "adminLogin";
    }

    @GetMapping("/adminProfile")
    public String  diretAdminProfilHit(Model model){
        return adminLoginEmpty(model);
    }

}
