package com.crio.learning_navigator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.crio.learning_navigator.entity.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    
}
