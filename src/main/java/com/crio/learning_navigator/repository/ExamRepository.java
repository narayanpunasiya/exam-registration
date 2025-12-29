package com.crio.learning_navigator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.crio.learning_navigator.entity.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    
}
