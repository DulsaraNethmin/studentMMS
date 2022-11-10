package com.example.studentmms.service;

import com.example.studentmms.model.Teacher;
import com.example.studentmms.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    public Teacher addTeacher(Teacher teacher){
        return teacherRepository.insert(teacher);
    }

    public Teacher getTeacherByEmailPassword(String email,String password){
        Teacher teacher = teacherRepository.getTeacherByEmailPassword(email,password);
        /*Take the email and pw passed from teacher repository and pass to controller*/
        System.out.println(teacher);
        return teacher;
    }
}
