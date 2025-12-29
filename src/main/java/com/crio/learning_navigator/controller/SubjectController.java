package com.crio.learning_navigator.controller;

import java.util.List;
import com.crio.learning_navigator.dto.SubjectDTO;
import com.crio.learning_navigator.entity.Subject;
import com.crio.learning_navigator.mapper.EntityMapper;
import com.crio.learning_navigator.service.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectService service;

    public SubjectController(SubjectService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SubjectDTO> create(@RequestBody Subject subject) {
        return new ResponseEntity<>(
                EntityMapper.toSubjectDTO(service.createSubject(subject)),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public SubjectDTO get(@PathVariable("id") Long id) {
        return EntityMapper.toSubjectDTO(service.getSubject(id));
    }

    @GetMapping
    public List<SubjectDTO> getAll() {
        return service.getAllSubjects()
                      .stream()
                      .map(EntityMapper::toSubjectDTO)
                      .toList();
    }
}
