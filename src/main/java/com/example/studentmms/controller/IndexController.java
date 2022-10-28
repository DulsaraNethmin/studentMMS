package com.example.studentmms.controller;

import com.example.studentmms.model.Result;
import com.example.studentmms.model.Search;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("search",new Search());
        return "index";
    }
}
