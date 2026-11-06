package com.javabuilder.backendservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "userHasRole")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserHasRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;
}
