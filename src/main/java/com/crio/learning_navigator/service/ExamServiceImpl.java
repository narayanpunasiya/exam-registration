package com.crio.learning_navigator.service;

import java.util.List;
import com.crio.learning_navigator.entity.Exam;
import com.crio.learning_navigator.entity.Subject;
import com.crio.learning_navigator.exception.BadRequestException;
import com.crio.learning_navigator.exception.ResourceNotFoundException;
import com.crio.learning_navigator.repository.ExamRepository;
import com.crio.learning_navigator.repository.SubjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ExamServiceImpl implements ExamService{
    private final ExamRepository examRepo;
    private final SubjectRepository subjectRepo;

    public ExamServiceImpl(ExamRepository examRepo, SubjectRepository subjectRepo){
        this.examRepo = examRepo;
        this.subjectRepo = subjectRepo;
    }

    @Override
    public Exam createExam(Long subjectId){
        Subject subject = subjectRepo.findById(subjectId)
            .orElseThrow(()-> new ResourceNotFoundException("subject not found"));
        
        List<Exam> existingExams = examRepo.findAll();
        boolean alreadyExists = existingExams.stream()
                                             . anyMatch(exam -> exam.getSubject().getId().equals(subjectId));
                                             
        if(alreadyExists){
            throw new BadRequestException("Exam already exists for this subject");
        }

        Exam exam =  new Exam();
        exam.setExamName(subject.getSubjectName() + "EXAM");
        exam.setSubject(subject);

            return examRepo.save(exam);        
    }

    @Override
    public Exam getExam(Long id){
        return examRepo.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Exam not found"));
    }

    @Override
    public List<Exam> getAllExams(){
        return examRepo.findAll();
    }
}
