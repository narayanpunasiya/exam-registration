package com.crio.learning_navigator.controllerTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import com.crio.learning_navigator.controller.StudentController;
import com.crio.learning_navigator.entity.Student;
import com.crio.learning_navigator.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    private Student student;

    @BeforeEach
    void setup() {
        student = new Student();
        student.setId(1L);
        student.setName("Akash");
        student.setEnrolledSubjects(new ArrayList<>());
        student.setEnrolledExams(new ArrayList<>());
    }

    @Test
    void createStudent_shouldReturn201() throws Exception {
        when(studentService.createStudent(any(Student.class))).thenReturn(student);

        mockMvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                      "name": "Akash"
                    }
                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Akash"))
                .andExpect(jsonPath("$.enrolledSubjects").isArray())
                .andExpect(jsonPath("$.enrolledExams").isArray());
    }

    @Test
    void enrollSubject_shouldReturnDTO() throws Exception {
        doReturn(student)
                .when(studentService)
                .enrollSubject(1L, 1L);

        mockMvc.perform(post("/students/1/subjects/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Akash"))
                .andExpect(jsonPath("$.enrolledSubjects").isEmpty())
                .andExpect(jsonPath("$.enrolledExams").isEmpty());
    }

    @Test
    void enrollExam_shouldReturnDTO() throws Exception {
        doReturn(student)
                .when(studentService)
                .enrollExam(1L, 1L);

        mockMvc.perform(post("/students/1/exams/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Akash"))
                .andExpect(jsonPath("$.enrolledSubjects").isEmpty())
                .andExpect(jsonPath("$.enrolledExams").isEmpty());
    }

    @Test
    void getStudent_shouldReturnDTO() throws Exception {
        when(studentService.getStudent(1L)).thenReturn(student);

        mockMvc.perform(get("/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Akash"))
                .andExpect(jsonPath("$.enrolledSubjects").isEmpty())
                .andExpect(jsonPath("$.enrolledExams").isEmpty());
    }
}
