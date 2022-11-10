package com.example.studentmms.service;

import com.example.studentmms.model.Teacher;
import com.example.studentmms.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class TeacherService {

    @Autowired
    TeacherRepository teacherRepository;


    PasswordEncoder passwordEncoder;
    public Teacher addTeacher(Teacher teacher){
        this.passwordEncoder=new BCryptPasswordEncoder();
        String encoded_password= passwordEncoder.encode(teacher.getPassword());
        teacher.setPassword(encoded_password);
        return teacherRepository.insert(teacher);
    }

    public Boolean getTeacherByEmailPassword(String email,String password){
        Teacher teacher = teacherRepository.getTeacherByEmailPassword(email);
        this.passwordEncoder=new BCryptPasswordEncoder();
        Boolean is_password_ok=passwordEncoder.matches(password,teacher.getPassword());
        System.out.println(teacher);
        return is_password_ok;
    }

    public Teacher getATeacher(String email){
        Teacher teacher = teacherRepository.getTeacherByEmailPassword(email);
        return teacher;
    }
}
