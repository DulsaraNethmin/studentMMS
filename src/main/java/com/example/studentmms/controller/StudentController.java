package com.example.studentmms.controller;

import com.example.studentmms.model.Student;
import com.example.studentmms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/student/show")
    public String showStudentshow(Model model){
        System.out.println("show student");
        List<Student> students= studentService.getStudentBYTeacher("t-0011");
        System.out.println(students.size());
        model.addAttribute("student",new Student());
        model.addAttribute("students",students);
        return "show-student";
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
    @GetMapping("/student/{index}")
    public ResponseEntity<?> getSinglestudent(@PathVariable("index") String index){
        try{
            Student res = studentService.getStudentById(index);
            return new ResponseEntity(res,HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<String>("Internal server error..", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/update/{id}")
    public String handleStudentUpdate(@PathVariable("id") String id,Model model) {

 model.addAttribute("editStudent",studentService.getStudentById(id));
 return "edit_student";

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
//    @PutMapping("/update/{id}")
//    public ResponseEntity<?> updateStudent(@PathVariable("id") String id, @RequestBody Student st){
//        Optional <Student> student=studentService.getStudent(id);
//        if(student.isPresent()){
//            Student st1=student.get();
//            st1.setName(st.getName()!=null?st.getName():st1.getName());
//            studentService.saveUpdated(st1);
//            return  new ResponseEntity<>(st1,HttpStatus.OK);
//        }
//        else {
//            return new ResponseEntity<>("There is no any matching values",HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @DeleteMapping("/delete/{index}")
//    public ResponseEntity<?> deleteStudent(@PathVariable("index") String index_No){
//        try {
//             studentService.delete(index_No);
//             return new ResponseEntity<>("Item deleted",HttpStatus.OK);
//        }catch (Exception e)
//        {
//              return new ResponseEntity<>("Not deleted",HttpStatus.NOT_FOUND);
//        }
    }







