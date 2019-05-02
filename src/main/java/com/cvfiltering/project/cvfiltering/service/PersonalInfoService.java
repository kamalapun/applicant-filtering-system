package com.cvfiltering.project.cvfiltering.service;

import com.cvfiltering.project.cvfiltering.entity.PersonalInfo;

import java.util.List;

public interface PersonalInfoService {
    PersonalInfo insert(PersonalInfo personalInfo);
    PersonalInfo getOne(Long id);
    List<PersonalInfo> getAll();

}
