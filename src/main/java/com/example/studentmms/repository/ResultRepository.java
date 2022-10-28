package com.example.studentmms.repository;

import com.example.studentmms.model.Result;
import com.example.studentmms.model.Term;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ResultRepository extends MongoRepository<Result,String> {

    @Query("{'index_no': ?0 ,'year':?1 , 'term':?2}")
    Result search(String index_no, String year, Term term);
}
