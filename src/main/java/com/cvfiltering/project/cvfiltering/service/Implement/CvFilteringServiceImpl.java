package com.cvfiltering.project.cvfiltering.service.Implement;

import com.cvfiltering.project.cvfiltering.entity.CvFiltering;
import com.cvfiltering.project.cvfiltering.entity.InputDataDTO;
import com.cvfiltering.project.cvfiltering.repository.CvFilteringRepository;
import com.cvfiltering.project.cvfiltering.service.CvFilteringService;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Data
public class CvFilteringServiceImpl implements CvFilteringService {
    private CvFilteringRepository cvFilteringRepository;
    Double entropy = 0.0;
    Double informationGain = 0.0;
    Double gain = 0.0;

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
    public void decisionTree(InputDataDTO inputDataDTO) {

        Double countPos = 0.0;
        Double countNeg = 0.0;
        List<String> posts =inputDataDTO.getJobPost();

        List<CvFiltering> cvFilteringlists = cvFilteringRepository.findAll();
        Double countJobPos = 0.0;
        Double countJobNeg = 0.0;

        Double countDeveloperPos = 0.0;
        Double countDeveloperNeg = 0.0;
        Double countQaPos = 0.0;
        Double countQaNeg = 0.0;
        Double countDbaPos = 0.0;
        Double countDbaNeg = 0.0;

        Double developerInformationGain = 0.0;
        Double qaInformationGain = 0.0;
        Double dbaInformationGain = 0.0;

        Double developerEntropy = 0.0;
        Double qaEntropy = 0.0;
        Double dbaEntropy = 0.0;


        for(CvFiltering filtering:cvFilteringlists){
            if(filtering.getQualified() == "yes"){
                countPos++;
            }else{
                countNeg++;
            }
        }

        informationGain = (-countPos/(countPos + countNeg)) * (Math.log((countPos/
                (countPos + countNeg)))/Math.log(2));

        for(CvFiltering filtering:cvFilteringlists){
            if(filtering.getJobPost() == "Developer" &&
                    filtering.getQualified() == "yes"){
                countDeveloperPos++;
            }
            else{
                countDeveloperNeg++;
            }
        }

        developerInformationGain = (-countDeveloperPos/(countDeveloperPos + countDeveloperNeg))
                * (Math.log((countDeveloperPos/
                (countDeveloperPos + countDeveloperNeg)))/Math.log(2));

        developerEntropy = ((countDeveloperPos + countDeveloperNeg)/
                (countPos + countNeg))*developerInformationGain;


        for(CvFiltering filtering:cvFilteringlists){
            if(filtering.getJobPost() == "QA"&& filtering.getQualified() == "yes"){
                countQaPos++;
            }else{
                countQaNeg++;
            }
        }

        qaInformationGain = (-countQaPos/(countQaPos + countQaNeg))
                * (Math.log((countQaPos/
                (countQaPos + countQaNeg)))/Math.log(2));
        qaEntropy = ((countQaPos + countQaNeg)/
                (countPos + countNeg))*qaInformationGain;



        for(CvFiltering filtering:cvFilteringlists){
            if(filtering.getJobPost() == "DBA"&& filtering.getQualified() == "yes"){
                countDbaPos++;
            }else{
                countDbaNeg++;
            }
        }

        dbaInformationGain = (-countDbaPos/(countDbaPos + countDbaNeg))
                * (Math.log((countDbaPos/
                (countDbaPos + countDbaNeg)))/Math.log(2));
        dbaEntropy = ((countDbaPos + countDbaNeg)/
                (countPos + countNeg))*qaInformationGain;

    }
}
