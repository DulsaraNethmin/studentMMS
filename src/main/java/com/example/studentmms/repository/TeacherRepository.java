package com.example.studentmms.repository;

import com.example.studentmms.model.Student;
import com.example.studentmms.model.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends MongoRepository<Teacher,String> {
    @Query("{'email':?0}")
    Teacher getTeacherByEmailPassword(String email);
}
