package com.example.studentmms.repository;

import com.example.studentmms.model.Result;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResultRepository extends MongoRepository<Result,String> {
}
