package com.crio.learning_navigator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    private Long id;
    private String name;

    private List<Long> enrolledSubjectIds;
    private List<Long> enrolledExamIds;
}
