package com.crio.learning_navigator.controllerTest;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.crio.learning_navigator.controller.ExamController;
import com.crio.learning_navigator.entity.Exam;
import com.crio.learning_navigator.entity.Subject;
import com.crio.learning_navigator.service.ExamService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = ExamController.class)
@AutoConfigureMockMvc(addFilters = false)
class ExamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExamService examService;

    private Exam testExam;

    @BeforeEach
    void setUp() {
        Subject subject = new Subject(1L, "ENGLISH", new ArrayList<>());
        testExam = new Exam(1L, "ENGLISHEXAM", subject, new ArrayList<>());
    }

    @Test
    void testCreateExam() throws Exception {
        Mockito.when(examService.createExam(1L)).thenReturn(testExam);

        mockMvc.perform(post("/exams/subjects/1"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.examName").value("ENGLISHEXAM"))
                .andExpect(jsonPath("$.subjectId").value(1))
                .andExpect(jsonPath("$.studentIds").isArray())
                .andExpect(jsonPath("$.studentIds").isEmpty());
    }

    @Test
    void testGetExamById() throws Exception {
        Mockito.when(examService.getExam(1L)).thenReturn(testExam);

        mockMvc.perform(get("/exams/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.examName").value("ENGLISHEXAM"))
                .andExpect(jsonPath("$.subjectId").value(1))
                .andExpect(jsonPath("$.studentIds").isArray())
                .andExpect(jsonPath("$.studentIds").isEmpty());
    }

    @Test
    void testGetAllExams() throws Exception {
        Mockito.when(examService.getAllExams()).thenReturn(List.of(testExam));

        mockMvc.perform(get("/exams"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].examName").value("ENGLISHEXAM"))
                .andExpect(jsonPath("$[0].subjectId").value(1))
                .andExpect(jsonPath("$[0].studentIds").isArray())
                .andExpect(jsonPath("$[0].studentIds").isEmpty());
    }
}
