package com.example.studentmms.service;

import com.example.studentmms.model.Result;
import com.example.studentmms.model.Student;
import com.example.studentmms.model.Subject;
import com.example.studentmms.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    PasswordEncoder passwordEncoder;
    public Student addStudent(Student student){
        this.passwordEncoder=new BCryptPasswordEncoder();
        return studentRepository.insert(student);
    }

    public List<Student> getStudentBYTeacher(String teacher_id){
        List<Student> students = studentRepository.findByTeacherId(teacher_id);
        return students;
    }
    public Student getStudentById(String index_no){
        Student student = studentRepository.getStudentById(index_no);
        return student;
    }
    public void updateStudent(Student student) {
        studentRepository.save(student);
    }
    public void deleteStudent(String id){
      studentRepository.deleteById(id);
    }

}
