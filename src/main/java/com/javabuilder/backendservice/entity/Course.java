package com.javabuilder.backendservice.entity;

import com.javabuilder.backendservice.common.CourseLevel;
import com.javabuilder.backendservice.common.CourseStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "courses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 510)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String thumbnail;
    private CourseLevel courseLevel;
    private CourseStatus courseStatus;
    private Instant createdAt;
    private Instant updatedAt;

    @OneToMany(mappedBy = "course")
    private Set<Chapter> chapters;
}
