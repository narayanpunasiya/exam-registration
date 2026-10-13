package com.lms.exam_registration.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "exams")
@Data
@NoArgsConstructor
public class Exam {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "exam_name", nullable = false, unique = true)
  private String examName;

  @ManyToMany(mappedBy = "enrolledExams")
  private Set<Student> students = new HashSet<>();

  @ManyToOne(
      fetch = FetchType.LAZY)
  @JoinColumn(name = "subject_id", nullable = false)
  private Subject subject;
}
