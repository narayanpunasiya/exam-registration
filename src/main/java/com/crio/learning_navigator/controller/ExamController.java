package com.crio.learning_navigator.controller;

import com.crio.learning_navigator.dto.ExamDTO;
import com.crio.learning_navigator.entity.Exam;
import com.crio.learning_navigator.mapper.EntityMapper;
import com.crio.learning_navigator.service.ExamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exams")
public class ExamController {

    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @PostMapping("/subjects/{subjectId}")
    public ResponseEntity<ExamDTO> create(@PathVariable Long subjectId) {
        Exam exam = examService.createExam(subjectId);
        return new ResponseEntity<>(EntityMapper.toExamDTO(exam), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamDTO> get(@PathVariable Long id) {
        Exam exam = examService.getExam(id);
        return ResponseEntity.ok(EntityMapper.toExamDTO(exam));
    }

    @GetMapping
    public ResponseEntity<List<ExamDTO>> getAll() {
        List<ExamDTO> exams = EntityMapper.toExamDTOList(examService.getAllExams());
        return ResponseEntity.ok(exams);
    }
}
