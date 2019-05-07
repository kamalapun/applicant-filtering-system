package com.cvfiltering.project.cvfiltering.service.Implement;

import com.cvfiltering.project.cvfiltering.entity.CvFiltering;
import com.cvfiltering.project.cvfiltering.entity.InputDataDTO;
import com.cvfiltering.project.cvfiltering.repository.CvFilteringRepository;
import com.cvfiltering.project.cvfiltering.service.CvFilteringService;
import javafx.beans.binding.IntegerBinding;
import lombok.Data;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Data
public class CvFilteringServiceImpl implements CvFilteringService {
    Double infornationGain=0.0;
    Double Entropy=0.0;
    Double gain=0.0;



    private CvFilteringRepository cvFilteringRepository;

    public CvFilteringServiceImpl(CvFilteringRepository cvFilteringRepository) {
        this.cvFilteringRepository = cvFilteringRepository;
    }

    @Override
    public CvFiltering insert(CvFiltering cvFiltering) {
        CvFiltering cvFiltering1 = cvFilteringRepository.save(cvFiltering);
        return cvFiltering1;
    }

    @Override
    public CvFiltering getOne(Long id) {
        return cvFilteringRepository.findById(id).get();
    }

    @Override
    public List<CvFiltering> getAll() {
        return cvFilteringRepository.findAll();
    }

    @Override
    public void decisionTreeAlgorithm(InputDataDTO inputDataDTO) {
        Double countPos=0.0;
        Double countNeg=0.0;
        List<String> jobPost=inputDataDTO.getJobPost();
        List<String> qualification=inputDataDTO.getQualification();
        List<Integer> experience=inputDataDTO.getExperience();
        List<String> skill=inputDataDTO.getSkill();
        List<String> interactivity=inputDataDTO.getInteractivity();

        List<CvFiltering> cvFilteringList=cvFilteringRepository.findAll();


        for(CvFiltering filtering: cvFilteringList){
               if(cvFilteringList.
        }



        Double countJobPos=0.0;
        Double countJobNeg=0.0;







    }
}
