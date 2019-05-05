package com.cvfiltering.project.cvfiltering.repository;

import com.cvfiltering.project.cvfiltering.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepositroy extends JpaRepository<Company,Long> {
}
