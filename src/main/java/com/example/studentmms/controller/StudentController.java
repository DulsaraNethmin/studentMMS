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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    @GetMapping("/student/show/{teacher_id}")
    public String showStudentshow(@PathVariable String teacher_id, Model model){
        System.out.println("show student");
        List<Student> students= studentService.getStudentBYTeacher(teacher_id);
        System.out.println(students.size());
        model.addAttribute("student",new Student());
        model.addAttribute("students",students);
        return "show-student";
    }

    @PostMapping("/student/add")
    public String addStudent(@ModelAttribute Student student, BindingResult result, Model model){
        System.out.println(student.toString());

        //model.addAttribute("Student",new Student());
        try{
            Student res=studentService.addStudent(student);
            return "teacher-home";
            //return new ResponseEntity("Success",HttpStatus.OK);
        }catch(Exception e){
            //return new ResponseEntity("Internal Server Error.",HttpStatus.INTERNAL_SERVER_ERROR);
            return "error";
        }
        //return "index";
    }

    @GetMapping("/update/{id}")
    public String handleStudentUpdate(@PathVariable("id") String id,Model model) {

        model.addAttribute("student",studentService.getStudentById(id));
        return "edit-student";

    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable("id") String id,@ModelAttribute("editStudent") Student student,Model model){
        Student s=studentService.getStudentById(id);
        student.setIndex_no(s.getIndex_no());
        student.setTeacher(s.getTeacher());
        student.setSubjects(s.getSubjects());
        student.setSchool(s.getSchool());
        studentService.updateStudent(student);
        List<Student> students= studentService.getStudentBYTeacher("t-0011");
        System.out.println(students.size());
        model.addAttribute("students",students);
        return "show-student";
    }
}
