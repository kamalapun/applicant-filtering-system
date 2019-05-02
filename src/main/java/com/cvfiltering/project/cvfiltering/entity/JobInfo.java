package com.cvfiltering.project.cvfiltering.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class JobInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String gender;
    private String post;
    private String education;
    private String experience;
    private String skills;
    private String language;
    private String description;
}
