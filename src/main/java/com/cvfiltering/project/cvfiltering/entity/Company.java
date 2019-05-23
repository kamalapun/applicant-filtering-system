package com.cvfiltering.project.cvfiltering.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table
public class Company {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
   // private String address;
  //  private String email;
    private String post;
    private String qualification;
    private Integer experience;
    private String skills;
    private String languages;
    private String interactivity;
    private String description;
}


