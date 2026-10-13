package com.lms.exam_registration.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.*;
import com.lms.exam_registration.entity.*;


@Entity
@Table(name = "subjects")
@Data
@NoArgsConstructor
public class Subject {

  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "subject_name", nullable = false, unique = true)
  private String subjectName;

  @ManyToMany(mappedBy = "subjects")
  private Set<Student> students = new HashSet<>();

}
