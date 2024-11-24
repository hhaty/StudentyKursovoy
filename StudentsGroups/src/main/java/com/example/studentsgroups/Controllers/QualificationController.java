package com.example.studentsgroups.Controllers;

import com.example.studentsgroups.DataModels.Qualification;
import com.example.studentsgroups.DataRepositories.QualificationRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/Qualification")
@Controller
@SessionAttributes({"qualifications", "account"})
public class QualificationController {
    private final QualificationRepository qualificationRepo;

    @Autowired
    public QualificationController(QualificationRepository qualificationRepo) {
        this.qualificationRepo = qualificationRepo;
    }

    @GetMapping
    public String getQualifications(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Qualification qualification = new Qualification();
        model.addAttribute("qualification", qualification);
        model.addAttribute("qualifications", qualificationRepo.findAll());
        model.addAttribute("account", userDetails==null? "Sign In" : userDetails.getUsername());
        return "Qualification/Qualification";
    }

    @PostMapping(value = "/create")
    public String createQualification(@Valid @ModelAttribute("qualification") Qualification qualification,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {return "Qualification/Qualification";}
        else {
            qualificationRepo.save(qualification);
            return "redirect:/Qualification";
        }
    }

    @PostMapping(value = "/delete")
    public String deleteQualification(@ModelAttribute(value = "var") Qualification qualification) {
        qualificationRepo.deleteById(qualification.getId());
        return "redirect:/Qualification";
    }

    @PostMapping(value = "/update")
    public String updateQualification(@AuthenticationPrincipal UserDetails userDetails,
                                      @ModelAttribute(value = "qualification") Qualification qualification, Model model) {
        model.addAttribute("qualification", qualification);
        model.addAttribute("qualifications", qualificationRepo.findAll());
        model.addAttribute("account", userDetails==null? "Sign In" : userDetails.getUsername());
        return "Qualification/QualificationUpdate";
    }

    @PostMapping(value = "/save")
    public String saveQualification(@Valid @ModelAttribute(value = "qualification") Qualification qualification,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {return "Qualification/QualificationUpdate";}
        else {
            qualificationRepo.save(qualification);
            return "redirect:/Qualification";
        }
    }

}
