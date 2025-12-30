package com.crio.learning_navigator.dto;

import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    private Long id;
    private String name;

    private List<SubjectDTO> enrolledSubjects = new ArrayList<>();
    private List<ExamDTO> enrolledExams = new ArrayList<>();

    private List<Long> enrolledSubjectIds = new ArrayList<>();
    private List<Long> enrolledExamIds = new ArrayList<>();
}

