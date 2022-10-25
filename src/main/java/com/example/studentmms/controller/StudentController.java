package com.example.studentmms.controller;

import com.example.studentmms.model.Student;
import com.example.studentmms.model.Subject;
import com.example.studentmms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {


    @Autowired
    StudentService studentService;
    @GetMapping("/student/add")
    public String showAddStudent(Model model){
        System.out.println("add student");
        model.addAttribute("student",new Student());
        return "add-student";
    }

    @PostMapping("/student/add")
    public String addStudent(@ModelAttribute Student student, BindingResult result, Model model){
        System.out.println(student.toString());

        model.addAttribute("Student",new Student());
        try{
            Student res=studentService.addStudent(student);
            return "redirect:";
            //return new ResponseEntity("Success",HttpStatus.OK);
        }catch(Exception e){
            //return new ResponseEntity("Internal Server Error.",HttpStatus.INTERNAL_SERVER_ERROR);
            return "";
        }
        //return "index";
    }
}
