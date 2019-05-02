package com.cvfiltering.project.cvfiltering.repository;

import com.cvfiltering.project.cvfiltering.entity.JobInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobInfoRepository extends JpaRepository<JobInfo,Long> {
}
