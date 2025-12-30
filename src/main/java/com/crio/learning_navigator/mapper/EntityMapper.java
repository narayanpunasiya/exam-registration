package com.crio.learning_navigator.mapper;

import java.util.ArrayList;
import java.util.List;
import com.crio.learning_navigator.dto.*;
import com.crio.learning_navigator.entity.*;

public class EntityMapper {

    public static StudentDTO toStudentDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
    
        dto.setEnrolledSubjects(
            student.getEnrolledSubjects() == null 
                ? new ArrayList<>() 
                : student.getEnrolledSubjects()
                         .stream()
                         .map(EntityMapper::toSubjectDTO)
                         .toList()
        );
    
        dto.setEnrolledExams(
            student.getEnrolledExams() == null 
                ? new ArrayList<>() 
                : student.getEnrolledExams()
                         .stream()
                         .map(EntityMapper::toExamDTO)
                         .toList()
        );
    
        dto.setEnrolledSubjectIds(
            student.getEnrolledSubjects() == null
                ? new ArrayList<>()
                : student.getEnrolledSubjects()
                         .stream()
                         .map(Subject::getId)
                         .toList()
        );
    
        dto.setEnrolledExamIds(
            student.getEnrolledExams() == null
                ? new ArrayList<>()
                : student.getEnrolledExams()
                         .stream()
                         .map(Exam::getId)
                         .toList()
        );
    
        return dto;
    }
    

    public static SubjectDTO toSubjectDTO(Subject subject) {
        SubjectDTO dto = new SubjectDTO();
        dto.setId(subject.getId());
        dto.setSubjectName(subject.getSubjectName());
        dto.setStudentIds(
            subject.getStudents().stream().map(Student::getId).toList()
        );
        return dto;
    }

   
    public static ExamDTO toExamDTO(Exam exam) {
        ExamDTO dto = new ExamDTO();
        dto.setId(exam.getId());
        dto.setExamName(exam.getExamName());
        dto.setSubjectId(exam.getSubject().getId());
        dto.setStudentIds(
            exam.getStudents().stream().map(Student::getId).toList()
        );
        return dto;
    }

    public static List<StudentDTO> toStudentDTOList(List<Student> allStudents) {
    if (allStudents == null) return List.of(); // return empty list if null
    return allStudents.stream()
            .map(EntityMapper::toStudentDTO)
            .toList();
}

public static List<SubjectDTO> toSubjectDTOList(List<Subject> allSubjects) {
    if (allSubjects == null) return List.of();
    return allSubjects.stream()
            .map(EntityMapper::toSubjectDTO)
            .toList();
}

public static List<ExamDTO> toExamDTOList(List<Exam> allExams) {
    if (allExams == null) return List.of();
    return allExams.stream()
            .map(EntityMapper::toExamDTO)
            .toList();
}

}
