package com.students.StudentManagement.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.students.StudentManagement.entity.Student;
import com.students.StudentManagement.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {StudentController.class})
@ExtendWith(SpringExtension.class)
class StudentControllerTest {
    @Autowired
    private StudentController studentController;

    @MockBean
    private StudentService studentService;

    /**
     * Method under test: {@link StudentController#findAllStudents()}
     */
    @Test
    void testFindAllStudents() throws Exception {
        when(studentService.findAll()).thenReturn(mock(Iterable.class));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/student");
        requestBuilder.accept("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(studentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(406));
    }

    /**
     * Method under test: {@link StudentController#getAll(String)}
     */
    @Test
    void testGetAll() throws Exception {
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/findAll")
                .contentType(MediaType.APPLICATION_JSON);
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content((new ObjectMapper()).writeValueAsString("foo"));
        MockMvcBuilders.standaloneSetup(studentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"errors\":[{\"message\":\"Invalid Syntax : offending token '\\\"foo\\\"' at line 1 column 1\",\"sourcePreview"
                                        + "\":\"\\\"foo\\\"\\n\",\"offendingToken\":\"\\\"foo\\\"\",\"locations\":[{\"line\":1,\"column\":1,\"sourceName\":null}],"
                                        + "\"errorType\":\"InvalidSyntax\",\"path\":null,\"extensions\":null}],\"data\":null,\"extensions\":null,\"dataPresent"
                                        + "\":false}"));
    }

    /**
     * Method under test: {@link StudentController#getAll(String)}
     */
    @Test
    void testGetAll2() throws Exception {
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/findAll", "Uri Variables")
                .contentType(MediaType.APPLICATION_JSON);
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content((new ObjectMapper()).writeValueAsString("foo"));
        MockMvcBuilders.standaloneSetup(studentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"errors\":[{\"message\":\"Invalid Syntax : offending token '\\\"foo\\\"' at line 1 column 1\",\"sourcePreview"
                                        + "\":\"\\\"foo\\\"\\n\",\"offendingToken\":\"\\\"foo\\\"\",\"locations\":[{\"line\":1,\"column\":1,\"sourceName\":null}],"
                                        + "\"errorType\":\"InvalidSyntax\",\"path\":null,\"extensions\":null}],\"data\":null,\"extensions\":null,\"dataPresent"
                                        + "\":false}"));
    }
}

