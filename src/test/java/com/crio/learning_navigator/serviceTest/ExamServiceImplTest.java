package com.crio.learning_navigator.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.crio.learning_navigator.dto.ExamDTO;
import com.crio.learning_navigator.entity.Exam;
import com.crio.learning_navigator.entity.Subject;
import com.crio.learning_navigator.exception.ResourceNotFoundException;
import com.crio.learning_navigator.mapper.EntityMapper;
import com.crio.learning_navigator.repository.ExamRepository;
import com.crio.learning_navigator.repository.SubjectRepository;
import com.crio.learning_navigator.service.ExamServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;  

@ExtendWith(MockitoExtension.class)
class ExamServiceImplTest {

    @Mock
    private ExamRepository examRepo;

    @Mock
    private SubjectRepository subjectRepo;

    @InjectMocks
    private ExamServiceImpl examService;

    @Test
    void createExam_shouldCreateExamForSubject() {
        Subject subject = new Subject();
        subject.setId(1L);
        subject.setSubjectName("MATH");

        when(subjectRepo.findById(1L)).thenReturn(Optional.of(subject));
        when(examRepo.save(any(Exam.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Exam exam = examService.createExam(1L);
        
        assertEquals("MATHEXAM", exam.getExamName());
        assertEquals(subject.getId(), exam.getSubject().getId());
        assertEquals(subject.getSubjectName(),exam.getSubject().getSubjectName());
    }

    @Test
    void createExam_shouldFailIfSubjectNotFound() {
        when(subjectRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> examService.createExam(1L));
    }

    @Test
    void getExam_shouldReturnExamById() {
        Subject subject = new Subject();
        subject.setId(1L);
        subject.setSubjectName("SCIENCE");

        Exam exam = new Exam();
        exam.setId(1L);
        exam.setExamName("SCIENCEEXAM");
        exam.setSubject(subject);
        exam.setStudents(new ArrayList<>());

        when(examRepo.findById(1L)).thenReturn(Optional.of(exam));

        Exam found = examService.getExam(1L);
        ExamDTO dto = EntityMapper.toExamDTO(found);

        assertEquals(1L, dto.getId());
        assertEquals("SCIENCEEXAM", dto.getExamName());
        assertEquals(1L, dto.getSubjectId());
        assertTrue(dto.getStudentIds().isEmpty());
    }

    @Test
    void getExam_shouldThrowIfNotFound() {
        when(examRepo.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> examService.getExam(1L));
    }

    @Test
    void getAllExams_shouldReturnListOfExams() {
        Subject subject1 = new Subject(1L, "MATH", new ArrayList<>());
        Subject subject2 = new Subject(2L, "SCIENCE", new ArrayList<>());

        Exam exam1 = new Exam(1L, "MATHEXAM", subject1, new ArrayList<>());
        Exam exam2 = new Exam(2L, "SCIENCEEXAM", subject2, new ArrayList<>());

        List<Exam> exams = List.of(exam1, exam2);

        when(examRepo.findAll()).thenReturn(exams);

        List<Exam> result = examService.getAllExams();
        List<ExamDTO> dtoList = EntityMapper.toExamDTOList(result);

        assertEquals(2, dtoList.size());
        assertEquals("MATHEXAM", dtoList.get(0).getExamName());
        assertEquals("SCIENCEEXAM", dtoList.get(1).getExamName());
        assertEquals(1L, dtoList.get(0).getSubjectId());
        assertEquals(2L, dtoList.get(1).getSubjectId());
    }
}

