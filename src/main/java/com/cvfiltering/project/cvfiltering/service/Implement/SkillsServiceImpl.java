package com.cvfiltering.project.cvfiltering.service.Implement;

import com.cvfiltering.project.cvfiltering.entity.Skills;
import com.cvfiltering.project.cvfiltering.repository.SkillsRepository;
import com.cvfiltering.project.cvfiltering.service.SkillsService;
import lombok.Data;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Data
@Service
@Transactional
public class SkillsServiceImpl implements SkillsService {
    private SkillsRepository skillsRepository;

    @Override
    public Skills insert(Skills skill) {
        Skills skills = skillsRepository.save(skill);
        return skills;
    }

    @Override
    public Skills update(Skills name, Long id) {
        Optional<Skills> skillswithoptional = skillsRepository.findById(id);
        if(skillswithoptional.isPresent()){
            Skills skillsvalue = skillswithoptional.get();
            skillsvalue.setSkill(name.getSkill());
            return skillsRepository.save(skillsvalue);
        }
        return null;
    }

    @Override
    public Skills getOne(Long id) {
        return skillsRepository.findById(id).get();
    }

    @Override
    public List<Skills> getAllSkills() {
        return skillsRepository.findAll(new Sort(Sort.Direction.ASC));
    }

}
