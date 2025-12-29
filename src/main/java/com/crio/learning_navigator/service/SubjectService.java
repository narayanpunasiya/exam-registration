package com.crio.learning_navigator.service;

import com.crio.learning_navigator.entity.Subject;
import java.util.List;

public interface SubjectService {

    Subject createSubject(Subject subject);

    Subject getSubject(Long id);

    List<Subject> getAllSubjects();
    
}