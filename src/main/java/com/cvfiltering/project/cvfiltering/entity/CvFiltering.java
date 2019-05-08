package com.cvfiltering.project.cvfiltering.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class CvFiltering {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String jobPost;
    private String qualification;
    private Integer experience;
    private String skill;
    private String interactivity;
    private String qualified;
}
