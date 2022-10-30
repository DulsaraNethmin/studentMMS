package com.example.studentmms.controller;

import com.example.studentmms.model.Result;
import com.example.studentmms.model.Search;
import com.example.studentmms.model.Student;
import com.example.studentmms.model.Term;
import com.example.studentmms.service.ResultService;
import com.example.studentmms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/result/add/{index_no}")
    public String addResult(@PathVariable String index_no,@ModelAttribute Result result, BindingResult res, Model model){
        try{
            System.out.println(result);
            Student student =studentService.getStudentById(index_no);
            System.out.println(student);
            result.setIndex_no(student.getIndex_no());
            result.setStudent(student.getName());
            resultService.addResult(result);
            return "teacher-home";
        }catch(Exception e){
            System.out.println(e.getMessage());
            return "error";
        }
    }

    @PostMapping("/search")
    public String search(@ModelAttribute Search search,BindingResult res,Model model){
        try{
            Result result=resultService.search(search);
            Student student = studentService.getStudentById(result.getIndex_no());
            model.addAttribute("result",result);
            model.addAttribute("student",student);
            System.out.println(result);
            return "index";
        }catch(Exception e){
            System.out.println(e.getMessage());
            return "error";
        }
    }

    @GetMapping("/show/result/{index_no}")
    public String showResultPage(@PathVariable String index_no,Model model){
        Student student =studentService.getStudentById(index_no);
        List<Result> results = resultService.getAllResult(index_no);
        model.addAttribute("results",results);
        model.addAttribute("student",student);
        return "show-result";
    }

    @GetMapping("/update/result/{index_no}/{year}/{term}")
    public String updateResult(@PathVariable String index_no,@PathVariable String year,@PathVariable Term term, Model model){
        System.out.println(index_no+" "+year+" "+term);
        Student student = studentService.getStudentById(index_no);
        Result result = resultService.search(new Search(index_no,year,term));
        model.addAttribute("student",student);
        model.addAttribute("result",result);
        return "update-result";
    }

    @PostMapping("/update/result/{index_no}/{year}/{term}")
    public String updateResult(@PathVariable String index_no,@PathVariable String year,@PathVariable Term term,@ModelAttribute Result result,BindingResult res, Model model){
        System.out.println("updateing...");

        //System.out.println(result);
        Result pre_result= resultService.search(new Search(index_no,year,term));
        System.out.println(pre_result);
        pre_result.setMarks(result.getMarks());
        Result updated_result=resultService.updateResult(pre_result);
        Student student =studentService.getStudentById(index_no);
        List<Result> results = resultService.getAllResult(index_no);
        model.addAttribute("results",results);
        model.addAttribute("student",student);
        //model.addAttribute("student",student);
        return "show-result";
    }
}
