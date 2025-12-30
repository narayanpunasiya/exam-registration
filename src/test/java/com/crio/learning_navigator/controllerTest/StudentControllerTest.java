package com.crio.learning_navigator.controllerTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import com.crio.learning_navigator.controller.StudentController;
import com.crio.learning_navigator.dto.StudentDTO;
import com.crio.learning_navigator.entity.Student;
import com.crio.learning_navigator.mapper.EntityMapper;
import com.crio.learning_navigator.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(StudentController.class)
@AutoConfigureMockMvc(addFilters = false)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    private Student student;
    private StudentDTO studentDTO;

    @BeforeEach
    void setup() {
        student = new Student();
        student.setId(1L);
        student.setName("Akash");
        student.setEnrolledSubjects(new ArrayList<>());
        student.setEnrolledExams(new ArrayList<>());

        studentDTO = new StudentDTO();
        studentDTO.setId(1L);
        studentDTO.setName("Akash");
        studentDTO.setEnrolledSubjects(new ArrayList<>());
        studentDTO.setEnrolledExams(new ArrayList<>());
    }

    @Test
    void createStudent_shouldReturn201() throws Exception {
        when(studentService.createStudent(any(Student.class))).thenReturn(student);

        try (MockedStatic<EntityMapper> mapper = mockStatic(EntityMapper.class)) {
            mapper.when(() -> EntityMapper.toStudentDTO(any(Student.class)))
                  .thenReturn(studentDTO);

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
    }

    @Test
    void enrollSubject_shouldReturnDTO() throws Exception {
        when(studentService.enrollSubject(1L, 1L)).thenReturn(student);

        try (MockedStatic<EntityMapper> mapper = mockStatic(EntityMapper.class)) {
            mapper.when(() -> EntityMapper.toStudentDTO(any(Student.class)))
                  .thenReturn(studentDTO);

                  mockMvc.perform(post("/students/1/subjects/1")
                  .contentType(MediaType.APPLICATION_JSON))
                  .andExpect(status().isOk())
                  .andExpect(jsonPath("$.id").value(1))
                  .andExpect(jsonPath("$.name").value("Akash"));
          
        }
    }

    @Test
    void enrollExam_shouldReturnDTO() throws Exception {
        when(studentService.enrollExam(1L, 1L)).thenReturn(student);

        try (MockedStatic<EntityMapper> mapper = mockStatic(EntityMapper.class)) {
            mapper.when(() -> EntityMapper.toStudentDTO(any(Student.class)))
                  .thenReturn(studentDTO);

                  mockMvc.perform(post("/students/1/exams/1")
                  .contentType(MediaType.APPLICATION_JSON))
                  .andExpect(status().isOk())
                  .andExpect(jsonPath("$.id").value(1))
                  .andExpect(jsonPath("$.name").value("Akash"));
        }
    }

    @Test
    void getStudent_shouldReturnDTO() throws Exception {
        when(studentService.getStudent(1L)).thenReturn(student);

        try (MockedStatic<EntityMapper> mapper = mockStatic(EntityMapper.class)) {
            mapper.when(() -> EntityMapper.toStudentDTO(any(Student.class)))
                  .thenReturn(studentDTO);

            mockMvc.perform(get("/students/1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.name").value("Akash"));
        }
    }
}
