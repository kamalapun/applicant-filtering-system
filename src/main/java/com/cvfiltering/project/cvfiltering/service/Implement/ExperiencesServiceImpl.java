package com.cvfiltering.project.cvfiltering.service.Implement;

import com.cvfiltering.project.cvfiltering.entity.Experiences;
import com.cvfiltering.project.cvfiltering.repository.ExperienceRepository;
import com.cvfiltering.project.cvfiltering.service.ExperiencesService;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Data
public class ExperiencesServiceImpl implements ExperiencesService {
    private ExperienceRepository experienceRepository;
    @Override
    public Experiences insert(Experiences experiences) {
        Experiences experiences1 = experienceRepository.save(experiences);
        return experiences1;
    }
}
