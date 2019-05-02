package com.cvfiltering.project.cvfiltering.service.Implement;

import com.cvfiltering.project.cvfiltering.entity.PersonalInfo;
import com.cvfiltering.project.cvfiltering.repository.PersonalInfoRepository;
import com.cvfiltering.project.cvfiltering.service.PersonalInfoService;
import lombok.Data;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Data
public class PersonalInfoServiceImpl implements PersonalInfoService{
    private PersonalInfoRepository personalInfoRepository;

    @Override
    public PersonalInfo insert(PersonalInfo personalInfo) {
        PersonalInfo personalInfo1 = personalInfoRepository.save(personalInfo);
        return personalInfo1;
    }

    @Override
    public PersonalInfo getOne(Long id) {
        return personalInfoRepository.findById(id).get();
    }

    @Override
    public List<PersonalInfo> getAll() {
        return personalInfoRepository.findAll(new Sort(Sort.Direction.ASC));
    }
}
