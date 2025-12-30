package com.crio.learning_navigator.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    private List<Subject> enrolledSubjects = new ArrayList<>();

    @ManyToMany
    @JoinTable(
    name = "student_enrolled_exams",
    joinColumns = @JoinColumn(name = "student_id"),
    inverseJoinColumns = @JoinColumn(name = "exam_id")
)
    private List<Exam> enrolledExams = new ArrayList<>();
    
}
