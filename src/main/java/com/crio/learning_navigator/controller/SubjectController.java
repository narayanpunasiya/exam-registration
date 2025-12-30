package com.crio.learning_navigator.controller;

import com.crio.learning_navigator.dto.SubjectDTO;
import com.crio.learning_navigator.entity.Subject;
import com.crio.learning_navigator.mapper.EntityMapper;
import com.crio.learning_navigator.service.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping
    public ResponseEntity<SubjectDTO> create(@RequestBody Subject subject) {
        Subject saved = subjectService.createSubject(subject);
        return new ResponseEntity<>(EntityMapper.toSubjectDTO(saved), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDTO> get(@PathVariable("id") Long id) {
        Subject subject = subjectService.getSubject(id);
        return ResponseEntity.ok(EntityMapper.toSubjectDTO(subject));
    }

    @GetMapping
    public ResponseEntity<List<SubjectDTO>> getAll() {
        List<SubjectDTO> subjects = EntityMapper.toSubjectDTOList(subjectService.getAllSubjects());
        return ResponseEntity.ok(subjects);
    }
}
