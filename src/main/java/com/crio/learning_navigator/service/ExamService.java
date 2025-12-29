package com.crio.learning_navigator.service;

import com.crio.learning_navigator.entity.Exam;
import java.util.List;

public interface ExamService {
    
    Exam createExam(Long subjectId);

    Exam getExam(Long id);

    List<Exam> getAllExams();
}
