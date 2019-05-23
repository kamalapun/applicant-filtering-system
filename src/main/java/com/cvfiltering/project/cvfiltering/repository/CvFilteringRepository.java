package com.cvfiltering.project.cvfiltering.repository;

import com.cvfiltering.project.cvfiltering.entity.CvFiltering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CvFilteringRepository extends JpaRepository<CvFiltering,Long> {
}
