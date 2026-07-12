package com.zidio.keystone.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false,unique = true, length = 13)
    private String phoneNumber;

    @Column(nullable = false)
    private boolean isActive;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;

}
