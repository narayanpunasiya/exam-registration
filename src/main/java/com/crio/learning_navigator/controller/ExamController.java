package com.crio.learning_navigator.controller;

import java.util.List;
import com.crio.learning_navigator.dto.ExamDTO;
import com.crio.learning_navigator.mapper.EntityMapper;
import com.crio.learning_navigator.service.ExamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exam")
public class ExamController {

    private final ExamService service;

    public ExamController(ExamService service) {
        this.service = service;
    }

    @PostMapping("/subjects/{subjectId}")
    public ResponseEntity<ExamDTO> create(@PathVariable("subjectId") Long subjectId) {
        return new ResponseEntity<>(
                EntityMapper.toExamDTO(service.createExam(subjectId)),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ExamDTO get(@PathVariable("id") Long id) {
        return EntityMapper.toExamDTO(service.getExam(id));
    }

    @GetMapping
    public List<ExamDTO> getAll() {
        return service.getAllExams()
                      .stream()
                      .map(EntityMapper::toExamDTO)
                      .toList();
    }
}
