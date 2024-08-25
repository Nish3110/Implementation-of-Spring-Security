package com.example.SpringBoot.SpringSecurity.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "posts")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
}
