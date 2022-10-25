package com.example.studentmms.service;

import com.example.studentmms.model.Student;
import com.example.studentmms.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public Student addStudent(Student student){
        return studentRepository.insert(student);
    }

    public List<Student> getStudentBYTeacher(String teacher_id){
        List<Student> students = studentRepository.findByTeacherId(teacher_id);
        return students;
    }
}
