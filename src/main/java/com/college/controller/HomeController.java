package com.college.controller;

import com.college.service.CollegeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final CollegeService collegeService;

    public HomeController(CollegeService collegeService) {
        this.collegeService = collegeService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("departments", collegeService.getDepartmentsByName());
        model.addAttribute("courses", collegeService.getCoursesByEnrollment());
        model.addAttribute("students", collegeService.getStudentsByGpa());
        return "index";
    }
}
