package com.cvfiltering.project.cvfiltering.service.Implement;

import com.cvfiltering.project.cvfiltering.entity.Education;
import com.cvfiltering.project.cvfiltering.repository.EducationRepository;
import com.cvfiltering.project.cvfiltering.service.EducationService;
import lombok.Data;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Data
public class EducationServiceImpl implements EducationService {
    private EducationRepository educationRepository;
    @Override
    public Education insert(Education education) {
        Education education1 = educationRepository.save(education);
        return education1;
    }

    @Override
    public Education getOne(Long id) {
        return educationRepository.findById(id).get();
    }

    @Override
    public List<Education> getAll() {
        return educationRepository.findAll(new Sort(Sort.Direction.ASC));
    }
}
