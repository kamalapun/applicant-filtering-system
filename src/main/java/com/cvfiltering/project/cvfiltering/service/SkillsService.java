package com.cvfiltering.project.cvfiltering.service;

import com.cvfiltering.project.cvfiltering.entity.Skills;

import java.util.List;

public interface SkillsService {
    Skills insert(Skills skill);
    Skills update(Skills name,Long id);
    Skills getOne(Long id);
    List<Skills> getAllSkills();
}
