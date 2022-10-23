package com.example.studentmms.controller;

import com.example.studentmms.model.Teacher;
import com.example.studentmms.repository.ResultRepository;
import com.example.studentmms.repository.StudentRepository;
import com.example.studentmms.repository.TeacherRepository;
import com.example.studentmms.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class TeacherController {


//    TeacherRepository teacherRepository;
//    StudentRepository studentRepository;
//    ResultRepository resultRepository;

    @Autowired
    private TeacherService teacherService;
    @GetMapping("/login")
    public String loginPage(){
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
}
