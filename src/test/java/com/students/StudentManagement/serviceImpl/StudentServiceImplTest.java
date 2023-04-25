package com.students.StudentManagement.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.students.StudentManagement.entity.Student;
import com.students.StudentManagement.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {StudentServiceImpl.class})
@ExtendWith(SpringExtension.class)
class StudentServiceImplTest {
    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    /**
     * Method under test: {@link StudentServiceImpl#save(Student)}
     */
    @Test
    void testSave() {
        Student student = new Student("42", "Student Name", "Oxford", "Marks");

        when(studentRepository.save(Mockito.<Student>any())).thenReturn(student);
        assertSame(student, studentServiceImpl.save(new Student("42", "Student Name", "Oxford", "Marks")));
        verify(studentRepository).save(Mockito.<Student>any());
    }

    /**
     * Method under test: {@link StudentServiceImpl#findAll()}
     */
    @Test
    void testFindAll() {
        when(studentRepository.findAll()).thenReturn(mock(Iterable.class));
        studentServiceImpl.findAll();
        verify(studentRepository).findAll();
    }
}

