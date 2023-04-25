package com.students.StudentManagement.exception;



public class StudentNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private String message;
    public StudentNotFoundException(){
    }
    public StudentNotFoundException(String message){
        super(message);
    }

}
