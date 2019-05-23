package com.cvfiltering.project.cvfiltering.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class CvFiltering {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String emailAddress;
    private String name;
    private String jobPost;
    private String qualification;
    private Integer experience;
    private String skill;
    private String interactivity;
    private Boolean qualified;
}
