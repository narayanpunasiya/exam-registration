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

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentDTO> create(@RequestBody Student student) {
        Student saved = studentService.createStudent(student);
        return new ResponseEntity<>(EntityMapper.toStudentDTO(saved), HttpStatus.CREATED);
    }

    @PostMapping("/{studentId}/subjects/{subjectId}")
    public ResponseEntity<StudentDTO> enrollSubject(
            @PathVariable("studentId") Long studentId,
            @PathVariable("subjectId") Long subjectId) {

                Student student = studentService.enrollSubject(studentId, subjectId);
                return ResponseEntity.ok(EntityMapper.toStudentDTO(student));
    }

    @PostMapping("/{studentId}/exams/{examId}")
    public ResponseEntity<StudentDTO> enrollExam(
            @PathVariable("studentId") Long studentId,
            @PathVariable("examId") Long examId) {

        Student updated = studentService.enrollExam(studentId, examId);
        return ResponseEntity.ok(EntityMapper.toStudentDTO(updated));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> get(@PathVariable("id") Long id) {
        Student student = studentService.getStudent(id);
        return ResponseEntity.ok(EntityMapper.toStudentDTO(student)); 
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAll() {
        List<StudentDTO> students = EntityMapper.toStudentDTOList(studentService.getAllStudents());
        return ResponseEntity.ok(students);
    }
}
