package com.crio.learning_navigator.service;

import jakarta.transaction.Transactional;
import java.util.List;
import com.crio.learning_navigator.entity.Exam;
import com.crio.learning_navigator.entity.Student;
import com.crio.learning_navigator.entity.Subject;
import com.crio.learning_navigator.exception.ResourceNotFoundException;
import com.crio.learning_navigator.repository.ExamRepository;
import com.crio.learning_navigator.repository.StudentRepository;
import com.crio.learning_navigator.repository.SubjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepo;
    private final SubjectRepository subjectRepo;
    private final ExamRepository examRepo;

    public StudentServiceImpl(StudentRepository studentRepo, 
                          SubjectRepository subjectRepo,
                          ExamRepository examRepo){
        this.studentRepo = studentRepo;
        this.subjectRepo = subjectRepo;
        this.examRepo = examRepo;
    }

    @Override
    public Student createStudent(Student student){
        return studentRepo.save(student);
    }

    @Override
    public Student getStudent(Long id){
        return studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    @Override
    public List<Student> getAllStudents(){
        return  studentRepo.findAll();
    }

    @Override
    @Transactional
    public Student enrollSubject(Long studentId, Long subjectId){
        Student student = studentRepo.findById(studentId)
                .orElseThrow(()-> new ResourceNotFoundException("Student not found"));

        Subject subject = subjectRepo.findById(subjectId)
                .orElseThrow(()-> new ResourceNotFoundException("Subject not found"));

        if(student.getEnrolledSubjects().contains(subject)){
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Student with id: " + studentId + " has already enrolled in subject"
                );
        }

        student.getEnrolledSubjects().add(subject); 
                
        return studentRepo.save(student);
    }

    @Override
    @Transactional
    public Student enrollExam(Long studentId, Long examId){
        Student student = studentRepo.findById(studentId)
                .orElseThrow(()-> new ResourceNotFoundException("Student not found"));
        Exam exam = examRepo.findById(examId)
            .orElseThrow(()-> new ResourceNotFoundException("Exam not found"));

        if(student.getEnrolledExams().contains(exam)){
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Student with id: " + studentId +
            " has already enrolled for this particular exam with id: " + examId
            );
        }

        student.getEnrolledExams().add(exam);
        return student;
        }  
}
