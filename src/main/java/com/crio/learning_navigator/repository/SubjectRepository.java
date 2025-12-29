package com.crio.learning_navigator.repository;

import java.util.Optional;
import com.crio.learning_navigator.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Optional<Subject> findBySubjectNameIgnoreCase(String subjectName);    
}
