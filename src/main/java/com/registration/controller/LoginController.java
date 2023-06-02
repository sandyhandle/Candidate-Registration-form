package com.registration.controller;

import com.registration.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @Autowired
    AdminController adminController;
    @GetMapping("/")
    public String loginLoder(Model model){
        if(Admin.isAdmin()){
            return adminController.adminEmptyloder(model);
        }
        return "index";
    }

    @PostMapping("/")
    public String loginLoderIndex(){
        return "index";
    }

    @PostMapping("/adminLogin")
    public String adminLoginLoder(){
        return "adminLogin";
    }

    @PostMapping("/candidateLogin")
    public String candidateLoginLoder(){
        return "candidateLogin";
    }
}
