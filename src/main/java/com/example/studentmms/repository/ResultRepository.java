package com.example.studentmms.repository;

import com.example.studentmms.model.Result;
import com.example.studentmms.model.Student;
import com.example.studentmms.model.Term;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ResultRepository extends MongoRepository<Result,String> {

    @Query("{'index_no': ?0 ,'year':?1 , 'term':?2}")
    Result search(String index_no, String year, Term term);

    @Query("{'index_no': ?0}")
    List<Result> getAllResult(String index_no);
}
