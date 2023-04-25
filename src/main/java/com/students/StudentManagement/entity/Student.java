package com.students.StudentManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Document(indexName = "student")
public class Student {
    @Id
    private String studentId;
    private String studentName;
    private String city;
    private String marks;
}
