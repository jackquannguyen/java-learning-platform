package com.javabuilder.backendservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String password; //ko nullable=false vì đ.nhập bằng GG pass là null
    private String displayName;
    private String avatarUrl;
    private Instant dateOfBirth;
    private Instant createdAt;
}
