package com.crio.learning_navigator.mapper;

import com.crio.learning_navigator.dto.*;
import com.crio.learning_navigator.entity.*;

import java.util.List;

public class EntityMapper {

    public static StudentDTO toStudentDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());

        dto.setEnrolledSubjectIds(
            student.getEnrolledSubjects() == null
                ? List.of()
                : student.getEnrolledSubjects()
                         .stream()
                         .map(Subject::getId)
                         .toList()
        );

        dto.setEnrolledExamIds(
            student.getEnrolledExams() == null
                ? List.of()
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
            subject.getStudents() == null
                ? List.of()
                : subject.getStudents()
                         .stream()
                         .map(Student::getId)
                         .toList()
        );

        return dto;
    }

    public static ExamDTO toExamDTO(Exam exam) {
        ExamDTO dto = new ExamDTO();
        dto.setId(exam.getId());
        dto.setExamName(exam.getExamName());
        dto.setSubjectId(exam.getSubject().getId());

        dto.setStudentIds(
            exam.getStudents() == null
                ? List.of()
                : exam.getStudents()
                      .stream()
                      .map(Student::getId)
                      .toList()
        );

        return dto;
    }

    public static List<ExamDTO> toExamDTOList(List<Exam> exams) {
        return exams.stream()
                .map(EntityMapper::toExamDTO)
                .toList();
    }

    public static List<SubjectDTO> toSubjectDTOList(List<Subject> subjects) {
        return subjects.stream()
                .map(EntityMapper::toSubjectDTO)
                .toList();
    }

    public static List<StudentDTO> toStudentDTOList(List<Student> students) {
        return students.stream()
                .map(EntityMapper::toStudentDTO)
                .toList();
    }
}
