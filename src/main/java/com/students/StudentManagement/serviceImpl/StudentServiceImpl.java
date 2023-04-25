package com.students.StudentManagement.serviceImpl;

import com.students.StudentManagement.entity.Student;
import com.students.StudentManagement.exception.StudentNotFoundException;
import com.students.StudentManagement.repository.StudentRepository;
import com.students.StudentManagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Iterable<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(String studentId) {
        Student student = null;
        try {
            student = studentRepository.findById(studentId).get();
        }
        catch (NoSuchElementException noSuchElementException){
            throw new StudentNotFoundException("Student with id "+studentId+" doesn't exist.");
        }
        return student;
    }
}

