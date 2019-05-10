package com.cvfiltering.project.cvfiltering.service.Implement;

import com.cvfiltering.project.cvfiltering.entity.Company;
import com.cvfiltering.project.cvfiltering.repository.CompanyRepositroy;
import com.cvfiltering.project.cvfiltering.service.CompanyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepositroy companyRepositroy;

    public CompanyServiceImpl(CompanyRepositroy companyRepositroy) {
        this.companyRepositroy = companyRepositroy;
    }

    @Override
    public Company insert(Company company) {
        Company company1 = companyRepositroy.save(company);
        return company1;
    }

    @Override
    public Company update(Company company, Long id) {
        Optional<Company> companyWithOptional = companyRepositroy.findById(id);
        if (companyWithOptional.isPresent()) {
            Company companyValueOptional = companyWithOptional.get();
            companyValueOptional.setName(company.getName());
           // companyValueOptional.setAddress(company.getAddress());
           // companyValueOptional.setEmail(company.getEmail());
            companyValueOptional.setPost(company.getPost());
            companyValueOptional.setQualification(company.getQualification());
            companyValueOptional.setExperience(company.getExperience());
            companyValueOptional.setSkills(company.getSkills());
            companyValueOptional.setInteractivity(company.getInteractivity());

            companyValueOptional.setLanguages(company.getLanguages());
            companyValueOptional.setDescription(company.getDescription());
            return companyRepositroy.save(companyValueOptional);
        }
        return null;
    }

    @Override
    public Company getOne(Long id) {
        return companyRepositroy.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        companyRepositroy.deleteById(id);
    }

    @Override
    public List<Company> getAll() {
        return companyRepositroy.findAll();
    }
}
