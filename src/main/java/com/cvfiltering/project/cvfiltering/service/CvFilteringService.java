package com.cvfiltering.project.cvfiltering.service;

import com.cvfiltering.project.cvfiltering.entity.CvFiltering;

import java.util.List;

public interface CvFilteringService {
    CvFiltering insert(CvFiltering cvFiltering);
    CvFiltering getOne(Long id);
    List<CvFiltering> getAll();
}
