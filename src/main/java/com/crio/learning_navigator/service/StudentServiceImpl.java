package com.crio.learning_navigator.service;

import org.springframework.stereotype.Service;
import com.crio.learning_navigator.repository.StudentRepository;
import com.crio.learning_navigator.repository.SubjectRepository;
import java.util.ArrayList;
import java.util.List;
import com.crio.learning_navigator.entity.Exam;
import com.crio.learning_navigator.entity.Student;
import com.crio.learning_navigator.entity.Subject;
import com.crio.learning_navigator.exception.BadRequestException;
import com.crio.learning_navigator.exception.ResourceNotFoundException;
import com.crio.learning_navigator.repository.ExamRepository;

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

    //create student
    @Override
    public Student createStudent(Student student){
        if(student.getEnrolledExams() == null){
            student.setEnrolledExams(new ArrayList<>());
        }
        if(student.getEnrolledSubjects() == null){
            student.setEnrolledSubjects(new ArrayList<>());
        }

        return studentRepo.save(student);
    }

    @Override
    public Student enrollSubject(Long studentId, Long subjectId){
        Student student = studentRepo.findById(studentId)
                .orElseThrow(()-> new ResourceNotFoundException("Student not found"));

        Subject subject = subjectRepo.findById(subjectId)
                .orElseThrow(()-> new ResourceNotFoundException("Subject not found"));

        if(student.getEnrolledSubjects().contains(subject)){
            throw new BadRequestException(
                "student with id " + studentId + " has already enrolled in subject"
                );
        }

        student.getEnrolledSubjects().add(subject); 
                
        return studentRepo.save(student);
    }

    @Override
    public Student enrollExam(Long studentId, Long examId){
        Student student = studentRepo.findById(studentId)
                .orElseThrow(()-> new ResourceNotFoundException("Student not found"));
        Exam exam = examRepo.findById(examId)
            .orElseThrow(()-> new ResourceNotFoundException("Exam not found"));

        if(!student.getEnrolledSubjects().contains(exam.getSubject())){
            throw new BadRequestException("Enroll in subject before registering for exam");
        }

        if(student.getEnrolledExams().contains(exam)){
            throw new BadRequestException(
                "Student with id: " + studentId +
            " has already enrolled for this particular exam with id: " + examId
            );
        }

        student.getEnrolledExams().add(exam);
        return studentRepo.save(student);
        }
    
    @Override
    public Student getStudent(Long id){
        return studentRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Student not found"));
    }

    @Override
    public List<Student> getAllStudents(){
        return  studentRepo.findAll();
    }
          
}
