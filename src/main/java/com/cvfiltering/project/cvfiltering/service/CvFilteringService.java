package com.cvfiltering.project.cvfiltering.service;

import com.cvfiltering.project.cvfiltering.entity.CvFiltering;
import com.cvfiltering.project.cvfiltering.entity.InputDataDTO;

import java.util.List;

public interface CvFilteringService {
    CvFiltering insert(CvFiltering cvFiltering);
    CvFiltering getOne(Long id);
    List<CvFiltering> getAll();
   void decisionTreeAlgorithm(InputDataDTO inputDataDTO);
}
