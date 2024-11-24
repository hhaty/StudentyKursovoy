package com.example.studentsgroups.Controllers;

import com.example.studentsgroups.DataModels.Speciality;
import com.example.studentsgroups.DataRepositories.SpecialityRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/Speciality")
@Controller
@SessionAttributes({"specialities", "account"})
public class SpecialityController {
    private final SpecialityRepository specialityRepo;

    @Autowired
    public SpecialityController(SpecialityRepository specialityRepo) {
        this.specialityRepo = specialityRepo;
    }

    @GetMapping
    public String getSpecialities(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Speciality speciality = new Speciality();
        model.addAttribute("speciality", speciality);
        model.addAttribute("specialities", specialityRepo.findAll());
        model.addAttribute("account", userDetails==null ? "Sign In" : userDetails.getUsername());
        return "Speciality/Speciality";
    }

    @PostMapping(value = "/create")
    public String createSpeciality(@Valid @ModelAttribute("speciality") Speciality speciality,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {return "Speciality/Speciality";}
        else {
            specialityRepo.save(speciality);
            return "redirect:/Speciality";
        }
    }

    @PostMapping(value = "/delete")
    public String deleteSpeciality(@ModelAttribute(value = "var") Speciality speciality) {
        specialityRepo.deleteById(speciality.getId());
        return "redirect:/Speciality";
    }

    @PostMapping(value = "/update")
    public String updateSpeciality(@AuthenticationPrincipal UserDetails userDetails,
                                   @ModelAttribute(value = "speciality") Speciality speciality, Model model) {
        model.addAttribute("speciality", speciality);
        model.addAttribute("specialities", specialityRepo.findAll());
        model.addAttribute("account", userDetails==null ? "Sign In" : userDetails.getUsername());
        return "Speciality/SpecialityUpdate";
    }

    @PostMapping(value = "/save")
    public String saveSpeciality(@Valid @ModelAttribute(value = "speciality") Speciality speciality,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {return "Speciality/SpecialityUpdate";}
        else {
            specialityRepo.save(speciality);
            return "redirect:/Speciality";
        }
    }

}
