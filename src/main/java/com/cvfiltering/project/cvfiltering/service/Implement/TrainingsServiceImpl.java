package com.cvfiltering.project.cvfiltering.service.Implement;

import com.cvfiltering.project.cvfiltering.entity.Trainings;
import com.cvfiltering.project.cvfiltering.repository.TrainingsRepository;
import com.cvfiltering.project.cvfiltering.service.TrainingsService;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Data
public class TrainingsServiceImpl implements TrainingsService {
    private TrainingsRepository trainingsRepository;

    @Override
    public Trainings insert(Trainings trainings) {
        Trainings trainings1 = trainingsRepository.save(trainings);
        return trainings1;
    }
}
