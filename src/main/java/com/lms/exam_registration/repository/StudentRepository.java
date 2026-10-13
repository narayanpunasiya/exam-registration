package com.lms.exam_registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lms.exam_registration.entity.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
  
}
