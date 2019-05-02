package com.cvfiltering.project.cvfiltering.repository;

import com.cvfiltering.project.cvfiltering.entity.PersonalInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalInfoRepository extends JpaRepository<PersonalInfo,Long> {
}
