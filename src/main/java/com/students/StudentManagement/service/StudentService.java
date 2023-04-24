package com.students.StudentManagement.service;

import com.students.StudentManagement.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface StudentService {
    Student save(Student student);

    List<Student> findAll();
}
