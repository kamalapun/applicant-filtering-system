package com.cvfiltering.project.cvfiltering.repository;

import com.cvfiltering.project.cvfiltering.entity.CvFiltering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CvFilteringRepository extends JpaRepository<CvFiltering,Long> {
    @Query("select i from CvFiltering i where experience=?1")
    CvFiltering getByExperience(Integer experience);

    @Query("select DISTINCT (?1) from cv_filtering")
    List<String> getUniqueColumnItems(String ColumnName);
}
