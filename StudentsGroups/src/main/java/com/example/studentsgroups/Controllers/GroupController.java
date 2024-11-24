package com.example.studentsgroups.Controllers;

import com.example.studentsgroups.DataModels.GroupStudents;
import com.example.studentsgroups.DataRepositories.FormEducationRepository;
import com.example.studentsgroups.DataRepositories.GroupRepository;
import com.example.studentsgroups.DataRepositories.QualificationRepository;
import com.example.studentsgroups.DataRepositories.SpecialityRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/Group")
@Controller
@SessionAttributes({"groups", "specialities", "qualifications", "forms", "account"})
public class GroupController {
    private final GroupRepository groupRepo;
    private final SpecialityRepository specialityRepo;
    private final QualificationRepository qualificationRepo;
    private final FormEducationRepository formEducationRepo;

    @Autowired
    public GroupController(GroupRepository groupRepo, SpecialityRepository specialityRepo,
                           QualificationRepository qualificationRepo, FormEducationRepository formEducationRepo) {
        this.groupRepo = groupRepo;
        this.specialityRepo = specialityRepo;
        this.qualificationRepo = qualificationRepo;
        this.formEducationRepo = formEducationRepo;
    }

    @GetMapping
    public String getGroups(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        GroupStudents group = new GroupStudents();
        model.addAttribute("group", group);
        model.addAttribute("groups", groupRepo.findAll());
        model.addAttribute("specialities", specialityRepo.findAll());
        model.addAttribute("qualifications", qualificationRepo.findAll());
        model.addAttribute("forms", formEducationRepo.findAll());
        model.addAttribute("account", userDetails==null ? "Sign In" : userDetails.getUsername());
        return "Group/Group";
    }

    @PostMapping(value = "/create")
    public String createGroup(@Valid @ModelAttribute("group") GroupStudents group, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {return "Group/Group";}
        else {
            groupRepo.save(group);
            return "redirect:/Group";
        }
    }

    @PostMapping(value = "/delete")
    public String deleteGroup(@ModelAttribute("var") GroupStudents group) {
        groupRepo.deleteById(group.getId());
        return "redirect:/Group";
    }

    @PostMapping(value = "/update")
    public String updateGroup(@AuthenticationPrincipal UserDetails userDetails,
                              @ModelAttribute("group") GroupStudents group, Model model) {
        model.addAttribute("group", group);
        model.addAttribute("groups", groupRepo.findAll());
        model.addAttribute("specialities", specialityRepo.findAll());
        model.addAttribute("qualifications", qualificationRepo.findAll());
        model.addAttribute("forms", formEducationRepo.findAll());
        model.addAttribute("account", userDetails==null ? "Sign In" : userDetails.getUsername());
        return "Group/GroupUpdate";
    }

    @PostMapping(value = "/save")
    public String saveGroup(@Valid @ModelAttribute("group") GroupStudents group, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {return "Group/GroupUpdate";}
        else {
            groupRepo.save(group);
            return "redirect:/Group";
        }
    }

}
