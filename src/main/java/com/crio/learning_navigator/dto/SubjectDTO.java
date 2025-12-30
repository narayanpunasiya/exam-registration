package com.crio.learning_navigator.dto;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDTO {

    private Long id;
    private String subjectName;
    
    private List<Long> studentIds;
}

