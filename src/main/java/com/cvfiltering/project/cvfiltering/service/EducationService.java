package com.cvfiltering.project.cvfiltering.service;

import com.cvfiltering.project.cvfiltering.entity.Education;

import java.util.List;

public interface EducationService {
    Education insert(Education name);
    Education update(Education name,Long id);
    void delete(Long id);
    Education getOne(Long id);
    List<Education> getAll();
}
