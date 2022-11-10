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

    @Autowired/*dependency injection*/
    /*low coupling(how much one class depends on another class). Therefore, create object in somewhere else and put here*/
    /*Then dependence of between this class and other class is less.*/
    private TeacherService teacherService;
    //get request
    @GetMapping("/login")
    public String loginPage(Model model){
        /*using model, we can send a variable or an obj using model clz*/
        model.addAttribute("teacher",new Teacher());
        /*send Teacher obj to through login using model (Teacher is a model)
        * pass empty obj of Teacher model from here. (There is a file called teacher in models file)*/

        /*model.addAttribute  pass empty obj called new Teacher() using teacher variable*/
        /*This obj goes to index.html as teacher*/
        return "login";
    }

    @GetMapping("teacher/home")
    public String teacherHome(){
        return "teacher-home";
    }

    @PostMapping("add/teacher") /*@RequestBody-Ask to assign body of the variable to this objective
    Store the data that are passed from frontend using model attribute.*/
    public ResponseEntity<?> addTeacher(@RequestBody Teacher teacher){
        try{
            Teacher res = teacherService.addTeacher(teacher);
            return new ResponseEntity(res,HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<String>("Internal server error..", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*call this function when submit the pw and email*/
    @PostMapping("/login")
    public String login(@ModelAttribute Teacher teacher, BindingResult result, Model model,HttpSession session){
        /*teacher is a variable and it takes obj(which consists pw and email) that passed from login form*/
        System.out.println(teacher);
        try{
            /*Controller calls "teacherService" function in services folder*/   /*and take email and pw that given from teacher repo*/
            Teacher tech=teacherService.getTeacherByEmailPassword(teacher.getEmail(),teacher.getPassword());
            if(tech !=null){
                /*Check whether there are that email and pw*/
                System.out.println("login ok");
                model.addAttribute("teacher",tech);
                session.setAttribute("name",tech.getName());
                /*Session stores data(here, name and id) temporarily*/
                session.setAttribute("id",tech.getTeacher_id());
                return "teacher-home";
            }else{
                System.out.println("login fail");
                /*if null, then go to login again*/
                return "login";
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            return "login";
        }
        //return "redirect:";
    }

    @GetMapping("/logout")
    public String logout(Model model){
        model.addAttribute("search",new Search());
        return "index";
    }
}
