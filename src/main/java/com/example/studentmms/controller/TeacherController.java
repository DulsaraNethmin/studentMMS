package com.example.studentmms.controller;

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

    @GetMapping("teacher/home")
    public String teacherHome(){
        return "teacher-home";
    }

    @PostMapping("add/teacher")
    public ResponseEntity<?> addTeacher(@RequestBody Teacher teacher){
        try{
            Teacher res = teacherService.addTeacher(teacher);
            return new ResponseEntity(res,HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<String>("Internal server error..", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Teacher teacher, BindingResult result, Model model,HttpSession session){
        System.out.println(teacher);
        try{
            Teacher tech=teacherService.getTeacherByEmailPassword(teacher.getEmail(),teacher.getPassword());
            if(tech !=null){
                System.out.println("login ok");
                model.addAttribute("teacher",tech);
                session.setAttribute("name",tech.getName());
                session.setAttribute("id",tech.getTeacher_id());
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
    public String logout(){
        return "index";
    }
}
