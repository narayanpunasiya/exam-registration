package com.crio.learning_navigator.service;

import com.crio.learning_navigator.entity.Student;
import java.util.List;

public interface StudentService {

    Student createStudent(Student student);

    Student enrollSubject(Long studentId, Long subjectId);

    Student enrollExam(Long studentId, Long examId);

    Student getStudent(Long id);

    List<Student> getAllStudents();
}
