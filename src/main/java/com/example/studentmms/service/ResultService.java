package com.example.studentmms.service;

import com.example.studentmms.model.Result;
import com.example.studentmms.model.Search;
import com.example.studentmms.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Result> getAllResult(String index_no){
        List<Result> results = resultRepository.getAllResult(index_no);
        return results;
    }

    public Result updateResult(Result result){
        return resultRepository.save(result);
    }

    public void deleteResult(Result result){
        resultRepository.delete(result);
    }
}
