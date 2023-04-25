package com.students.StudentManagement.controller;

import com.students.StudentManagement.entity.Student;
import com.students.StudentManagement.service.StudentService;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;




@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Value("classpath:student.graphqls")
    private Resource schemaResource;

    private GraphQL graphQL;
    @PostConstruct
    public void loadSchema() throws IOException {
        File schemaFile = schemaResource.getFile();
        TypeDefinitionRegistry registry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = builWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(registry,wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring builWiring(){
        DataFetcher<Iterable<Student>> fetcher1 = data -> {
            return studentService.findAll();
        };
        DataFetcher<Student> fetcher2 = stud -> {
            return studentService.save(stud.getArgument("studentId"));
        };
        return RuntimeWiring.newRuntimeWiring().type("Query",typeWriting ->
            typeWriting.dataFetcher("findAll",fetcher1).dataFetcher("addStudent",fetcher2)).build();

    }
    @PostMapping("/student")
    public ResponseEntity<Student> addStudent(@RequestBody Student student){
        return new ResponseEntity<>(studentService.save(student), HttpStatus.CREATED);
    }

    @GetMapping("/student")
    public ResponseEntity<Iterable<Student>> findAllStudents(){
        return new ResponseEntity<>(studentService.findAll(),HttpStatus.OK);
    }
    @PostMapping("/findAll")
    public ResponseEntity<Object> getAll(@RequestBody String query){
        ExecutionResult result = graphQL.execute(query);
        return new ResponseEntity<Object>(result,HttpStatus.OK);
    }
}
