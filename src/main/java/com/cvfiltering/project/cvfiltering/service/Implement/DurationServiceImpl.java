package com.cvfiltering.project.cvfiltering.service.Implement;

import com.cvfiltering.project.cvfiltering.entity.Duration;
import com.cvfiltering.project.cvfiltering.repository.DurationRepository;
import com.cvfiltering.project.cvfiltering.service.DurationService;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Data
public class DurationServiceImpl implements DurationService{
    private DurationRepository durationRepository;

    @Override
    public Duration insert(Duration duration) {
        Duration duration1 = durationRepository.save(duration);
        return duration1;

    }
}
