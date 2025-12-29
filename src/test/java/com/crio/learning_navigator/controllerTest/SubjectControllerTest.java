package com.crio.learning_navigator.controllerTest;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.crio.learning_navigator.controller.SubjectController;
import com.crio.learning_navigator.entity.Subject;
import com.crio.learning_navigator.service.SubjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(SubjectController.class)
public class SubjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubjectService subjectService;

    private ObjectMapper objectMapper = new ObjectMapper(); 
    private Subject testSubject;

    @BeforeEach
    void setUp() {
        testSubject = new Subject();
        testSubject.setId(1L);
        testSubject.setSubjectName("ENGLISH");
        testSubject.setStudents(new ArrayList<>());
    }

    @Test
    void testCreateSubject() throws Exception {
        when(subjectService.createSubject(any(Subject.class))).thenReturn(testSubject);

        mockMvc.perform(post("/subjects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testSubject)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.subjectName", is("ENGLISH")))
                .andExpect(jsonPath("$.studentIds").isEmpty());
    }

    @Test
    void testGetSubjectById() throws Exception {
        Mockito.when(subjectService.getSubject(1L)).thenReturn(testSubject);

        mockMvc.perform(get("/subjects/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id", is(1)))
               .andExpect(jsonPath("$.subjectName", is("ENGLISH")))
               .andExpect(jsonPath("$.studentIds").isEmpty());
    }

    @Test
    void testGetAllSubjects() throws Exception {
        List<Subject> subjects = Arrays.asList(testSubject);
        when(subjectService.getAllSubjects()).thenReturn(subjects);

        mockMvc.perform(get("/subjects"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].subjectName", is("ENGLISH")))
                .andExpect(jsonPath("$[0].studentIds").isEmpty());
    }
}
