package com.lms.exam_registration.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;
import com.lms.exam_registration.entity.*;

@Entity
@Table(name = "students")
@Getter 
@Setter
@NoArgsConstructor
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @ManyToMany
  @JoinTable(
      name = "student_subjects",
      joinColumns = @JoinColumn(name = "student_id"),
      inverseJoinColumns = @JoinColumn(name = "subject_id"))

  private Set<Subject> enrolledSubjects = new HashSet<>();


  @ManyToMany
  @JoinTable(
      name = "student_exams",
      joinColumns = @JoinColumn(name = "student_id"),
      inverseJoinColumns = @JoinColumn(name = "exam_id"))
  private Set<Exam> enrolledExams = new HashSet<>();
}
