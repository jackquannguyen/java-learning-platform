package com.javabuilder.backendservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "lessons")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    private String videoUrl;
    private int durationSeconds;
    private Boolean isFreePreview;
    private int orderIndex;
    private Instant createdAt;
    private Instant updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;
}
