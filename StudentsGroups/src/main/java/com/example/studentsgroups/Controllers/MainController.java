package com.example.studentsgroups.Controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String fun(@AuthenticationPrincipal UserDetails userDetails, Model model){
        model.addAttribute("authentication", "Войти");
        if(userDetails!=null){
            model.addAttribute("authentication", userDetails.getUsername());
        }
        return "Main";
    }
}