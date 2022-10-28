package com.example.studentmms.service;

import com.example.studentmms.model.Result;
import com.example.studentmms.model.Search;
import com.example.studentmms.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    public Result addResult(Result result){
        return resultRepository.insert(result);
    }

    public Result search(Search search){
        System.out.println(search);
        return resultRepository.search(search.getIndex(),search.getYear(),search.getTerm());
    }
}
