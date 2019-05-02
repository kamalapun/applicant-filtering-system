package com.cvfiltering.project.cvfiltering.service;

import com.cvfiltering.project.cvfiltering.entity.Education;

import java.util.List;

public interface EducationService {
    Education insert(Education education);
    Education getOne(Long id);
    List<Education> getAll();
}
