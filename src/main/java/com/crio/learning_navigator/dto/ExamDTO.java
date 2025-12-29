package com.crio.learning_navigator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamDTO {

    private Long id;
    private String examName;

    private Long subjectId;

    private List<Long> studentIds;
}
