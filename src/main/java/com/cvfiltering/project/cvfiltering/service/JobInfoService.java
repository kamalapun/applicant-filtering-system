package com.cvfiltering.project.cvfiltering.service;

import com.cvfiltering.project.cvfiltering.entity.JobInfo;

import java.util.List;

public interface JobInfoService {
    JobInfo insert(JobInfo jobInfo);
    JobInfo update(JobInfo jobInfo,Long id);
    void delete(Long id);
    JobInfo getOne(Long id);
    List<JobInfo> getAll();
}
