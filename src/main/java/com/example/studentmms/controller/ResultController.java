package com.example.studentmms.controller;

import com.example.studentmms.model.Result;
import com.example.studentmms.model.Student;
import com.example.studentmms.service.ResultService;
import com.example.studentmms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ResultController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ResultService resultService;
    @GetMapping("/add/result/{index_no}")
    public String showAddResult(@PathVariable("index_no") String index_no , Model model){
        System.out.println(index_no);
        Student student = studentService.getStudentById(index_no);
        System.out.println(student.getName());
        model.addAttribute("student",student);
        model.addAttribute("result",new Result());
        return "add-result";
    }

    @PostMapping("/result/add")
    public String addResult(@ModelAttribute Result result, BindingResult res, Model model){
        try{
            System.out.println(result);
            resultService.addResult(result);
            return "redirect:";
        }catch(Exception e){
            System.out.println(e.getMessage());
            return "error";
        }
    }
}
