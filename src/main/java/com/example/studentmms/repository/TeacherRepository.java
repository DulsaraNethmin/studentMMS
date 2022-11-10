package com.example.studentmms.repository;

import com.example.studentmms.model.Student;
import com.example.studentmms.model.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends MongoRepository<Teacher,String> {
    /*If there are data, then it returns to teacher service*/
    @Query("{'email':?0 ,'password': ?1}")
    Teacher getTeacherByEmailPassword(String email,String password);
}
