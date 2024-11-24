package com.example.studentsgroups.Controllers;

import com.example.studentsgroups.DataRepositories.UserModelRepository;
import com.example.studentsgroups.Forms.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Registration")
public class RegistrationController {
    private final UserModelRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public RegistrationController(UserModelRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping
    public String showRegistrationForm() {return "Registration";}
    @PostMapping
    public String processRegistration(RegistrationForm form) {
        userRepo.save(form.postUser(passwordEncoder));
        return "redirect:/Login";
    }
}
