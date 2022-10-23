package com.example.studentmms.controller;

import com.example.studentmms.model.Student;
import com.example.studentmms.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {

    @GetMapping("/student/add")
    public String showAddStudent(Model model){
        //Subject subject;
        //model.addAttribute("subject",Subject.);
        model.addAttribute("student",new Student());
        return "add-student";
    }

    @PostMapping("/student/add")
    public String addStudent(@ModelAttribute Student student, BindingResult result, Model model){
        System.out.println(student.toString());
        model.addAttribute("Student",new Student());
        return "index";
    }
}
