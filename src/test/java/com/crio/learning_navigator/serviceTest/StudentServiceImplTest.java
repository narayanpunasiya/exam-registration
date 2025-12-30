package com.crio.learning_navigator.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.crio.learning_navigator.entity.Exam;
import com.crio.learning_navigator.entity.Student;
import com.crio.learning_navigator.entity.Subject;
import com.crio.learning_navigator.repository.ExamRepository;
import com.crio.learning_navigator.repository.StudentRepository;
import com.crio.learning_navigator.repository.SubjectRepository;
import com.crio.learning_navigator.service.StudentServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepo;

    @Mock
    private SubjectRepository subjectRepo;

    @Mock
    private ExamRepository examRepo;

    @InjectMocks
    private StudentServiceImpl studentService;

    
    @Test
    void createStudent_shouldReturnSavedStudent() {
        Student student = new Student();
        student.setName("Akash");

        when(studentRepo.save(any(Student.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Student saved = studentService.createStudent(student);

        assertEquals("Akash", saved.getName());
    }

   
    @Test
    void enrollSubject_shouldAddSubject() {
        Student student = new Student();
        student.setEnrolledSubjects(new ArrayList<>());

        Subject subject = new Subject();
        subject.setId(1L);

        when(studentRepo.findById(1L)).thenReturn(Optional.of(student));
        when(subjectRepo.findById(1L)).thenReturn(Optional.of(subject));
        when(studentRepo.save(any(Student.class))).thenReturn(student);

        Student result = studentService.enrollSubject(1L, 1L);

        assertEquals(1, result.getEnrolledSubjects().size());
    }

    
    @Test
    void enrollExam_shouldPreventDuplicateEnrollment() {
        Subject subject = new Subject();

        Exam exam = new Exam();
        exam.setSubject(subject);

        Student student = new Student();
        student.setEnrolledSubjects(List.of(subject));
        student.setEnrolledExams(new ArrayList<>(List.of(exam)));

        when(studentRepo.findById(1L)).thenReturn(Optional.of(student));
        when(examRepo.findById(1L)).thenReturn(Optional.of(exam));

        assertThrows(ResponseStatusException.class,
                () -> studentService.enrollExam(1L, 1L));
    }
}
