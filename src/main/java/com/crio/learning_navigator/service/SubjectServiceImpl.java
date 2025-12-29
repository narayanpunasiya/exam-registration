package com.crio.learning_navigator.service;

import java.util.List;
import com.crio.learning_navigator.entity.Subject;
import com.crio.learning_navigator.exception.BadRequestException;
import com.crio.learning_navigator.exception.ResourceNotFoundException;
import com.crio.learning_navigator.repository.SubjectRepository;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepo;

    public SubjectServiceImpl (SubjectRepository subjectRepo){
        this.subjectRepo = subjectRepo;
    }

    @Override
    public Subject createSubject(Subject subject){
        subjectRepo.findBySubjectNameIgnoreCase(subject.getSubjectName())
            .ifPresent(existing->{
                throw new BadRequestException("Subject already exists");
            });
        return subjectRepo.save(subject);
    }

    @Override
    public Subject getSubject(Long id){
        return subjectRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("subject not foune"));
    }

    @Override
    public List<Subject> getAllSubjects(){
        return subjectRepo.findAll();
    }    
}
