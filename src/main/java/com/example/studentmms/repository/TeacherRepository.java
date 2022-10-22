package com.example.studentmms.repository;

import com.example.studentmms.model.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeacherRepository extends MongoRepository<Teacher,String> {
}
