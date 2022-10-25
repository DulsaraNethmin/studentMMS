package com.example.studentmms.repository;

import com.example.studentmms.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface StudentRepository extends MongoRepository<Student,String> {

    @Query("{'teacher': ?0}")
    List<Student> findByTeacherId(String teacher_id);
}
