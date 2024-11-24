package com.example.studentsgroups.Controllers;

import com.example.studentsgroups.DataModels.FormEducation;
import com.example.studentsgroups.DataRepositories.FormEducationRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/FormEducation")
@Controller
@SessionAttributes({"forms", "account"})
public class FormEducationController {
    private final FormEducationRepository formEducationRepo;

    @Autowired
    public FormEducationController(FormEducationRepository formEducationRepo) {
        this.formEducationRepo = formEducationRepo;
    }

    @GetMapping
    public String getForms(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        FormEducation formEducation = new FormEducation();
        model.addAttribute("form", formEducation);
        model.addAttribute("forms", formEducationRepo.findAll());
        model.addAttribute("account", userDetails==null ? "Sign in" : userDetails.getUsername());
        return "FormEducation/FormEducation";
    }

    @PostMapping(value = "/create")
    public String createForm(@Valid @ModelAttribute("form") FormEducation formEducation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {return "FormEducation/FormEducation";}
        else {
            formEducationRepo.save(formEducation);
            return "redirect:/FormEducation";
        }
    }

    @PostMapping(value = "/delete")
    public String deleteForm(@ModelAttribute(value = "var") FormEducation formEducation) {
        formEducationRepo.deleteById(formEducation.getId());
        return "redirect:/FormEducation";
    }

    @PostMapping(value = "/update")
    public String updateForm(@AuthenticationPrincipal UserDetails userDetails,
                             @ModelAttribute("form") FormEducation formEducation, Model model) {
        model.addAttribute("form", formEducation);
        model.addAttribute("forms", formEducationRepo.findAll());
        model.addAttribute("account", userDetails==null ? "Sign in" : userDetails.getUsername());
        return "FormEducation/FormEducationUpdate";
    }

    @PostMapping(value = "/save")
    public String saveForm(@Valid @ModelAttribute("form") FormEducation formEducation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {return "FormEducation/FormEducationUpdate";}
        else {
            formEducationRepo.save(formEducation);
            return "redirect:/FormEducation";
        }
    }

}
