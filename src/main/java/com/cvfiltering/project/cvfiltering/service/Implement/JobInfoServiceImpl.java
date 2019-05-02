package com.cvfiltering.project.cvfiltering.service.Implement;

import com.cvfiltering.project.cvfiltering.entity.JobInfo;
import com.cvfiltering.project.cvfiltering.repository.JobInfoRepository;
import com.cvfiltering.project.cvfiltering.service.JobInfoService;
import lombok.Data;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Data
public class JobInfoServiceImpl implements JobInfoService{
    private JobInfoRepository jobInfoRepository;

    @Override
    public JobInfo insert(JobInfo jobInfo) {
        JobInfo jobInfo1 = jobInfoRepository.save(jobInfo);
        return jobInfo1;
    }

    @Override
    public JobInfo update(JobInfo jobInfo, Long id) {
        Optional<JobInfo> jobInfowithoptional = jobInfoRepository.findById(id);
        if(jobInfowithoptional.isPresent()){
            JobInfo jobInfovalue= jobInfowithoptional.get();
            jobInfovalue.setCompanyName(jobInfo.getCompanyName());
            return jobInfoRepository.save(jobInfovalue);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        jobInfoRepository.deleteById(id);

    }

    @Override
    public JobInfo getOne(Long id) {
        return jobInfoRepository.findById(id).get();
    }

    @Override
    public List<JobInfo> getAll() {
        return jobInfoRepository.findAll(new Sort(Sort.Direction.ASC));
    }
}
