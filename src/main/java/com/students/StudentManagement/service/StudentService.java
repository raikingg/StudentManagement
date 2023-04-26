package com.students.StudentManagement.service;

import com.students.StudentManagement.entity.Student;
import org.springframework.stereotype.Service;


@Service
public interface StudentService {
    Student save(Student student);

    Iterable<Student> findAll();

    Student findById(String studentId);

    void deleteAll();

    void deleteById(String studentId);
}
