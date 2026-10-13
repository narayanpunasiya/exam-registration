package com.lms.exam_registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lms.exam_registration.entity.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {


  
}
