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
    public void decisionTreeAlgorithm(InputDataDTO inputDataDTO) {

        Double countPos = 0.0;
        Double countNeg = 0.0;
        List<String> jobPost =inputDataDTO.getJobPost();

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
        Double jobPostEntropy=0.0;
        Double jobPostGain;

        for(CvFiltering filtering:cvFilteringlists){
            if(filtering.getQualified().equals(1)){
                countPos++;
            }else{
                countNeg++;
            }
        }

        informationGain = (-countPos/(countPos + countNeg)) * (Math.log((countPos/ (countPos + countNeg)))/Math.log(2))-(countNeg/(countPos + countNeg)) * (Math.log((countNeg/ (countPos + countNeg)))/Math.log(2));

        for(CvFiltering filtering:cvFilteringlists){
            if(filtering.getJobPost() == "Developer" &&
                    filtering.getQualified().equals(1)){
                countDeveloperPos++;
            }
            else{
                countDeveloperNeg++;
            }
        }

        developerInformationGain = (-countDeveloperPos/(countDeveloperPos + countDeveloperNeg))
                * (Math.log((countDeveloperPos/
                (countDeveloperPos + countDeveloperNeg)))/Math.log(2))-(countDeveloperNeg/(countDeveloperPos + countDeveloperNeg))
                * (Math.log((countDeveloperNeg/
                (countDeveloperPos + countDeveloperNeg)))/Math.log(2));

        developerEntropy = ((countDeveloperPos + countDeveloperNeg)/
                (countPos + countNeg))*developerInformationGain;


        for(CvFiltering filtering:cvFilteringlists){
            if(filtering.getJobPost() == "QA"&& filtering.getQualified().equals(1)){
                countQaPos++;
            }else{
                countQaNeg++;
            }
        }

        qaInformationGain = (-countQaPos/(countQaPos + countQaNeg)) * (Math.log((countQaPos/
                (countQaPos + countQaNeg)))/Math.log(2))-(countQaNeg/(countQaPos + countQaNeg)) * (Math.log((countQaNeg/
                (countQaPos + countQaNeg)))/Math.log(2));

        qaEntropy = ((countQaPos + countQaNeg)/
                (countPos + countNeg))*qaInformationGain;



        for(CvFiltering filtering:cvFilteringlists){
            if(filtering.getJobPost() == "DBA"&& filtering.getQualified().equals(1)){
                countDbaPos++;
            }else{
                countDbaNeg++;
            }
        }

        dbaInformationGain = (-countDbaPos/(countDbaPos + countDbaNeg))
                * (Math.log((countDbaPos/
                (countDbaPos + countDbaNeg)))/Math.log(2))-(countDbaNeg/(countDbaPos + countDbaNeg))
                * (Math.log((countDbaNeg/
                (countDbaPos + countDbaNeg)))/Math.log(2));

        dbaEntropy = ((countDbaPos + countDbaNeg)/
                (countPos + countNeg))*dbaInformationGain;

        jobPostEntropy=developerEntropy+qaEntropy+dbaEntropy;
        jobPostGain=informationGain-jobPostEntropy;
        System.out.println(jobPostGain);

        Double countQualificationPos= 0.0;
        Double countQualificationNeg = 0.0;
        for(CvFiltering filtering:cvFilteringlists){
            if(filtering.getQualified().equals(1)){
                countQualificationPos++;
            }
            else{
                countQualificationNeg++;
            }
        }

        Double countMasterPos = 0.0;
        Double countMasterNeg = 0.0;
        Double countBachelorPos = 0.0;
        Double countBachelorNeg = 0.0;
        Double masterInformationGain = 0.0;
        Double bachelorInformationGain = 0.0;
        Double masterEntropy = 0.0;
        Double bachelorEntropy = 0.0;
        Double qualifictionEntropy = 0.0;
        Double qualificationGain = 0.0;

        for(CvFiltering filtering:cvFilteringlists){
            if(filtering.getQualification() == "Master" &&
                    filtering.getQualified().equals(1)){
                countMasterPos++;
            }
            else{
                countMasterNeg++;
            }
        }

        masterInformationGain = (-countMasterPos/(countMasterPos + countMasterNeg)) *
                (Math.log((countMasterPos/ (countMasterPos + countMasterNeg)))/
                        Math.log(2))-(countMasterNeg/(countMasterPos + countMasterNeg))
                * (Math.log((countMasterNeg/ (countMasterPos + countMasterNeg)))/Math.log(2));
        masterEntropy = ((countMasterPos + countMasterNeg)/
                (countPos + countNeg))*masterInformationGain;

        for(CvFiltering filtering:cvFilteringlists){
            if(filtering.getQualification() == "Bachelor" &&
                    filtering.getQualified().equals(1)){
                countBachelorPos++;
            }
            else{
                countBachelorNeg++;
            }
        }
        bachelorInformationGain = (-countBachelorPos/(countBachelorPos + countBachelorNeg)) *
                (Math.log((countBachelorPos/ (countBachelorPos + countBachelorNeg)))/
                        Math.log(2))-(countBachelorNeg/(countBachelorPos + countBachelorNeg))
                * (Math.log((countBachelorNeg/ (countBachelorPos + countBachelorNeg)))/Math.log(2));

        bachelorEntropy = ((countBachelorPos + countBachelorNeg)/
                (countPos + countNeg))*bachelorInformationGain;

        qualifictionEntropy =masterEntropy + bachelorEntropy;
        qaInformationGain = informationGain - qualifictionEntropy;
        System.out.println(qualificationGain);


        Double countExperiencePos = 0.0;
        Double countExperienceNeg = 0.0;
        Double experienceEntropy = 0.0;
        Double experienceGain = 0.0;
        Double countZeroPos = 0.0;
        Double countZeroNeg = 0.0;
        Double countOnePos = 0.0;
        Double countOneNeg = 0.0;
        Double countTwoPos = 0.0;
        Double countTwoNeg = 0.0;
        Double oneInformationGain = 0.0;
        Double twoInformationGain = 0.0;
        Double zeroInformationGain = 0.0;
        Double zeroEntropy = 0.0;
        Double oneEntropy = 0.0;
        Double twoEntropy = 0.0;


        for(CvFiltering filtering:cvFilteringlists){
            if(filtering.getQualified().equals(1)){
                countExperiencePos++;
            }
            else{
                countExperienceNeg++;
            }
        }



        for(CvFiltering filtering:cvFilteringlists){
            if(filtering.getExperience() == 0 &&
                    filtering.getQualified().equals(1)){
                countZeroPos++;
            }
            else{
                countZeroNeg++;
            }
        }

        zeroInformationGain = (-countZeroPos/(countZeroPos + countZeroNeg)) *
                (Math.log((countZeroPos/ (countZeroPos + countZeroNeg)))/
                        Math.log(2))-(countZeroNeg/(countZeroPos + countZeroNeg))
                * (Math.log((countZeroNeg/ (countZeroPos + countZeroNeg)))/Math.log(2));
        zeroEntropy = ((countZeroPos + countZeroNeg)/
                (countPos + countNeg))*zeroInformationGain;

        for(CvFiltering filtering:cvFilteringlists){
            if(filtering.getExperience() == 1 &&
                    filtering.getQualified().equals(1)){
                countOnePos++;
            }
            else{
                countOneNeg++;
            }
        }
        oneInformationGain = (-countOnePos/(countOnePos + countOneNeg)) *
                (Math.log((countOnePos/ (countOnePos + countOneNeg)))/
                        Math.log(2))-(countOneNeg/(countOnePos + countOneNeg))
                * (Math.log((countOneNeg/ (countOnePos + countOneNeg)))/Math.log(2));
        oneEntropy = ((countOnePos + countOneNeg)/
                (countPos + countNeg))*oneInformationGain;

        for(CvFiltering filtering:cvFilteringlists){
            if(filtering.getExperience() == 2 &&
                    filtering.getQualified().equals(1)){
                countTwoPos++;
            }
            else{
                countTwoNeg++;
            }
        }
        twoInformationGain = (-countTwoPos/(countTwoPos + countTwoNeg)) *
                (Math.log((countTwoPos/(countTwoPos +countTwoNeg)))/
                        Math.log(2))-(countTwoNeg/(countTwoPos +countTwoNeg))
                * (Math.log(( countTwoNeg/(countTwoPos +countTwoNeg)))/Math.log(2));
        twoEntropy = ((countTwoPos +  countTwoNeg)/
                (countPos + countNeg))*twoInformationGain;

        experienceEntropy = zeroEntropy + oneEntropy + twoEntropy;
        experienceGain = informationGain - experienceEntropy;

        Double countSkillPos = 0.0;
        Double countSkillNeg = 0.0;
        Double skillInformationGain = 0.0;
        Double skillGain = 0.0;
        Double skillEntropy = 0.0;
        Double countHighPos = 0.0;
        Double countHighNeg = 0.0;
        Double countMediumPos = 0.0;
        Double countMediumNeg = 0.0;
        Double countLowPos = 0.0;
        Double countLowNeg = 0.0;
        Double highEntropy = 0.0;
        Double mediumEntropy = 0.0;
        Double lowEntropy = 0.0;
        Double highInformationGain = 0.0;
        Double mediumInformationGain = 0.0;
        Double lowInformationGain = 0.0;

        for(CvFiltering filtering:cvFilteringlists){
            if(filtering.getSkill().equals(1)){
                countSkillPos++;
            }
            else{
                countSkillNeg++;
            }
        }


        for(CvFiltering filtering:cvFilteringlists){
            if(filtering.getSkill() == "high" &&
                    filtering.getQualified().equals(1)){
                countHighPos++;
            }
            else{
                countHighNeg++;
            }
        }
        highInformationGain = (-countHighPos/(countHighPos + countHighNeg)) *
                (Math.log((countHighPos/(countHighPos + countHighNeg)))/
                        Math.log(2))-( countHighPos/(countHighPos + countHighNeg))
                * (Math.log((countHighNeg/(countHighPos + countHighNeg)))/Math.log(2));
        highEntropy = ((countHighPos+    countHighNeg)/
                (countPos + countNeg))*highInformationGain;


        for(CvFiltering filtering:cvFilteringlists){
            if(filtering.getSkill() == "medium" &&
                    filtering.getQualified().equals(1)){
                countMediumPos++;
            }
            else{
                countMediumNeg++;
            }
        }
        mediumInformationGain = (-countMediumPos/(countMediumPos + countMediumNeg)) *
                (Math.log((countMediumPos/(countMediumPos + countMediumNeg)))/
                        Math.log(2))-(countMediumNeg/(countMediumPos +   countMediumNeg))
                * (Math.log((countMediumNeg/(countMediumPos + countMediumNeg)))/Math.log(2));
        mediumEntropy = ((countMediumPos + countMediumNeg)/
                (countPos + countNeg))*mediumInformationGain;

        for(CvFiltering filtering:cvFilteringlists){
            if(filtering.getSkill() == "low" &&
                    filtering.getQualified().equals(1)){
                countLowPos++;
            }
            else{
                countLowNeg++;
            }
        }
        lowInformationGain = (-countLowPos/(countLowPos + countLowNeg)) *
                (Math.log((countLowPos/(countLowPos + countLowNeg)))/
                        Math.log(2))-(countLowNeg/(countLowPos + countLowNeg))
                * (Math.log((countLowNeg/ (countLowPos + countLowNeg)))/Math.log(2));
        lowEntropy = ((countLowPos+ countLowNeg)/
                (countPos + countNeg))*lowInformationGain;

        skillEntropy = highEntropy + mediumEntropy +lowEntropy;
        skillGain = informationGain - skillEntropy;


        Double countInteractivityPos = 0.0;
        Double countInteractivityNeg = 0.0;
        Double interactivityInformationGain = 0.0;
        Double interactivityGain = 0.0;
        Double interactivityEntropy = 0.0;
        Double countInteractivityHighPos = 0.0;
        Double countInteractivityHighNeg = 0.0;
        Double countInteractivityMediumPos = 0.0;
        Double countInteractivityMediumNeg = 0.0;
        Double countInteractivityLowPos = 0.0;
        Double countInteractivityLowNeg = 0.0;
        Double highInteractivityEntropy = 0.0;
        Double mediumInteractivityEntropy = 0.0;
        Double lowInteractivityEntropy = 0.0;
        Double highInteractivityInformationGain = 0.0;
        Double mediumInteractivityInformationGain = 0.0;
        Double lowInteractivityInformationGain = 0.0;

        for(CvFiltering filtering:cvFilteringlists){
            if(filtering.getQualified().equals(1)){
                countInteractivityPos++;
            }
            else{
                countInteractivityNeg++;
            }
        }

        for(CvFiltering filtering:cvFilteringlists){
            if(filtering.getInteractivity() == "high" &&
                    filtering.getQualified().equals(1)){
                countInteractivityHighPos++;
            }
            else{
                countInteractivityHighNeg++;
            }
        }
        highInteractivityInformationGain = (-countInteractivityHighPos/(countInteractivityHighPos + countInteractivityHighNeg)) *
                (Math.log((countInteractivityHighPos/(countInteractivityHighPos + countInteractivityHighNeg)))/
                        Math.log(2))-(countInteractivityHighNeg/(countInteractivityHighPos+ countInteractivityHighNeg))
                * (Math.log((countInteractivityHighNeg/ (countInteractivityHighPos + countInteractivityHighNeg)))/Math.log(2));
       highInteractivityEntropy= ((countInteractivityHighPos+ countInteractivityHighNeg)/
                (countPos + countNeg))*highInteractivityInformationGain;


        for(CvFiltering filtering:cvFilteringlists){
            if(filtering.getInteractivity() == "medium" &&
                    filtering.getQualified().equals(1)){
                countInteractivityMediumPos++;
            }
            else{
                countInteractivityMediumNeg++;
            }
        }
        mediumInteractivityInformationGain = (-countInteractivityMediumPos/(countInteractivityMediumPos + countInteractivityMediumNeg)) *
                (Math.log((countInteractivityMediumPos/(countInteractivityMediumPos + countInteractivityMediumNeg)))/
                        Math.log(2))-(countInteractivityMediumNeg/(countInteractivityMediumPos+ countInteractivityMediumNeg))
                * (Math.log((countInteractivityMediumNeg/ (countInteractivityMediumPos + countInteractivityMediumNeg)))/Math.log(2));
        mediumInteractivityEntropy= ((countInteractivityMediumPos+ countInteractivityMediumNeg)/
                (countPos + countNeg))*mediumInteractivityInformationGain;


        for(CvFiltering filtering:cvFilteringlists){
            if(filtering.getInteractivity() == "low" &&
                    filtering.getQualified().equals(1)){
                countInteractivityLowPos++;
            }
            else{
                countInteractivityLowNeg++;
            }
        }
        lowInteractivityInformationGain = (-countInteractivityLowPos/(countInteractivityLowPos + countInteractivityLowNeg)) *
                (Math.log((countInteractivityLowPos/(countInteractivityLowPos + countInteractivityLowNeg)))/
                        Math.log(2))-(countInteractivityLowNeg/(countInteractivityLowPos+ countInteractivityLowNeg))
                * (Math.log((countInteractivityLowNeg/ (countInteractivityLowPos + countInteractivityLowNeg)))/Math.log(2));
        lowInteractivityEntropy= ((countInteractivityLowPos+ countInteractivityLowNeg)/
                (countPos + countNeg))*lowInteractivityInformationGain;
        interactivityEntropy = highInteractivityEntropy + mediumInteractivityEntropy
                + lowInteractivityEntropy;
        interactivityGain = informationGain - interactivityEntropy;




    }


}
