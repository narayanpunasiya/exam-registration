package com.crio.learning_navigator.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.crio.learning_navigator.entity.Subject;
import com.crio.learning_navigator.repository.SubjectRepository;
import com.crio.learning_navigator.service.SubjectServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SubjectServiceImplTest {

    @Mock
    private SubjectRepository subjectRepo;

    @InjectMocks
    private SubjectServiceImpl subjectService;


    @Test
    void createSubject_shouldSaveSubject() {
        Subject subject = new Subject();
        subject.setSubjectName("ENGLISH");

        when(subjectRepo.save(subject)).thenReturn(subject);

        Subject saved = subjectService.createSubject(subject);

        assertEquals("ENGLISH", saved.getSubjectName());
    }

    @Test
    void getSubject_shouldThrowRuntimeExceptionIfNotFound() {
        when(subjectRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> subjectService.getSubject(1L));
    }
}
