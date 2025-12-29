package com.crio.learning_navigator.controller;

import java.util.List;
import com.crio.learning_navigator.dto.StudentDTO;
import com.crio.learning_navigator.entity.Student;
import com.crio.learning_navigator.mapper.EntityMapper;
import com.crio.learning_navigator.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<StudentDTO> create(@RequestBody Student student) {
        Student saved = service.createStudent(student);
        return new ResponseEntity<>(EntityMapper.toStudentDTO(saved), HttpStatus.CREATED);
    }

    @PostMapping("/{studentId}/subjects/{subjectId}")
    public ResponseEntity<StudentDTO> enrollSubject(
            @PathVariable("studentId") Long studentId,
            @PathVariable("subjectId") Long subjectId) {

                Student student = service.enrollSubject(studentId, subjectId);
                return ResponseEntity.ok(EntityMapper.toStudentDTO(student));
    }

    @PostMapping("/{studentId}/exams/{examId}")
    public StudentDTO enrollExam(
            @PathVariable("studentId") Long studentId,
            @PathVariable("examId") Long examId) {

        return EntityMapper.toStudentDTO(
                service.enrollExam(studentId, examId)
        );
    }

    @GetMapping("/{id}")
    public StudentDTO get(@PathVariable("id") Long id) {
        return EntityMapper.toStudentDTO(service.getStudent(id));
    }

    @GetMapping
    public List<StudentDTO> getAll() {
        return service.getAllStudents()
                      .stream()
                      .map(EntityMapper::toStudentDTO)
                      .toList();
    }
}
