package com.example.studentmms.controller;

import com.example.studentmms.model.Search;
import com.example.studentmms.model.Student;
import com.example.studentmms.model.Teacher;
import com.example.studentmms.repository.ResultRepository;
import com.example.studentmms.repository.StudentRepository;
import com.example.studentmms.repository.TeacherRepository;
import com.example.studentmms.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;

@Controller
public class TeacherController {


//    TeacherRepository teacherRepository;
//    StudentRepository studentRepository;
//    ResultRepository resultRepository;

    @Autowired
    private TeacherService teacherService;
    @GetMapping("/login")
    public String loginPage(Model model){
        model.addAttribute("teacher",new Teacher());
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("teacher",new Teacher());
        return "register";
    }

    @GetMapping("teacher/home")
    public String teacherHome(HttpSession session){
        String n= session.getAttribute("name").toString();
        if(n!=null){
            return "teacher-home";
        }else{
            return "index";
        }

    }

    @PostMapping("/add/teacher")
    public String addTeacher(@ModelAttribute Teacher teacher, Model model, BindingResult result){
        try{
            Teacher res = teacherService.addTeacher(teacher);
            return "login";
        }catch(Exception e){
            System.out.println(e.getMessage());
            return "error";
        }
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Teacher teacher, BindingResult result, Model model,HttpSession session){
        System.out.println(teacher);
        try{
            Boolean tech=teacherService.getTeacherByEmailPassword(teacher.getEmail(),teacher.getPassword());
            if(tech){
                Teacher t= teacherService.getATeacher(teacher.getEmail());
                System.out.println("login ok");
                model.addAttribute("teacher",tech);
                session.setAttribute("name",t.getName());
                session.setAttribute("id",t.getTeacher_id());
                return "teacher-home";
            }else{
                System.out.println("login fail");
                return "login";
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            return "login";
        }
        //return "redirect:";
    }

    @GetMapping("/logout")
    public String logout(Model model,HttpSession session){
        model.addAttribute("search",new Search());
        session.removeAttribute("name");
        session.removeAttribute("id");
        return "index";
    }
}
