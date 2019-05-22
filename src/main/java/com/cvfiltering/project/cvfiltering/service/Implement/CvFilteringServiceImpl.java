package com.cvfiltering.project.cvfiltering.service.Implement;

import com.cvfiltering.project.cvfiltering.entity.CvFiltering;
import com.cvfiltering.project.cvfiltering.entity.InputDataDTO;
import com.cvfiltering.project.cvfiltering.repository.CvFilteringRepository;
import com.cvfiltering.project.cvfiltering.service.CvFilteringService;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@Data
public class CvFilteringServiceImpl implements CvFilteringService {
    private CvFilteringRepository cvFilteringRepository;
    Double OverAllEntropy = 0.0;
    Double OverAllInformationGain = 0.0;
    Double OverAllGain = 0.0;
    Double countOverAllPos = 0.0;
    Double countOverAllNeg = 0.0;
    List<String> root = new ArrayList<>();



    Double countExpPos=0.0;
    Double countExpNeg=0.0;
    Double overAllExpInformationGain=0.0;


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


    Double getInformationGain(List<CvFiltering> cvFilteringList) {
        for (CvFiltering filtering : cvFilteringList) {
            if (filtering.getQualified()) { 
                countOverAllPos++;
            } else {
                countOverAllNeg++;
            }
        }
        return (-countOverAllPos / (countOverAllPos + countOverAllNeg)) * (Math.log((countOverAllPos / (countOverAllPos + countOverAllNeg))) / Math.log(2)) -
                (countOverAllNeg / (countOverAllPos + countOverAllNeg)) * (Math.log((countOverAllNeg / (countOverAllPos + countOverAllNeg))) / Math.log(2));
    }

    Map<String, Double> getInformationGainEntropyForJobPost(List<CvFiltering> cvFilteringlists, String jobPost) {
        Double countPos = 0.0;
        Double countNeg = 0.0;
        Double informationGain = 0.0;
        Double entropy = 0.0;

        Map<String, Double> data = new HashMap();

        for (CvFiltering filtering : cvFilteringlists) {
            if (filtering.getJobPost().equals(jobPost) && filtering.getQualified()) {
                countPos++;
            } else if(filtering.getJobPost().equals(jobPost) && !filtering.getQualified()){
                countNeg++;
            }
        }

        informationGain = (-countPos / (countPos + countNeg)) * (Math.log((countPos / (countPos + countNeg))) / Math.log(2)) - (countNeg / (countPos + countNeg)) * (Math.log((countNeg / (countPos + countNeg))) / Math.log(2));
        entropy = ((countPos + countNeg) / (this.countOverAllPos + this.countOverAllNeg)) * informationGain;
        data.put("informationGain", informationGain);
        data.put("entropy", entropy);
        return data;
    }


    Map<String, Double> getInformationGainEntropyForQualification(List<CvFiltering> cvFilteringlists, String qualification) {
        Double countPos = 0.0;
        Double countNeg = 0.0;
        Double informationGain = 0.0;
        Double entropy = 0.0;
        Map<String, Double> data = new HashMap();
        for (CvFiltering filtering : cvFilteringlists) {
            if (filtering.getQualification().equals(qualification) && filtering.getQualified()) {
                countPos++;
            } else if(filtering.getQualification().equals(qualification) && !filtering.getQualified()){
                countNeg++;
            }
        }
        informationGain = (-countPos / (countPos + countNeg)) * (Math.log((countPos / (countPos + countNeg))) / Math.log(2)) - (countNeg / (countPos + countNeg)) * (Math.log((countNeg / (countPos + countNeg))) / Math.log(2));
        entropy = ((countPos + countNeg) / (this.countOverAllPos + this.countOverAllNeg)) * informationGain;
        data.put("informationGain", informationGain);
        data.put("entropy", entropy);
        return data;
    }


    Map<String, Double> getInformationGainEntropyForExperience(List<CvFiltering> cvFilteringlists, Integer experience) {
        Double countPos = 0.0;
        Double countNeg = 0.0;
        Double informationGain = 0.0;
        Double entropy = 0.0;
        Map<String, Double> data = new HashMap();
        for (CvFiltering filtering : cvFilteringlists) {
            if (filtering.getExperience().equals(experience) && filtering.getQualified()) {
                countPos++;
            } else if(filtering.getExperience().equals(experience) && !filtering.getQualified()){
                countNeg++;
            }
        }
        informationGain = (-countPos / (countPos + countNeg)) * (Math.log((countPos / (countPos + countNeg))) / Math.log(2)) - (countNeg / (countPos + countNeg)) * (Math.log((countNeg / (countPos + countNeg))) / Math.log(2));
        entropy = ((countPos + countNeg) / (this.countOverAllPos + this.countOverAllNeg)) * informationGain;
        data.put("informationGain", informationGain);
        data.put("entropy", entropy);
        return data;
    }

    Map<String, Double> getInformationGainEntropyForSkill(List<CvFiltering> cvFilteringlists, String skill) {
        Double countPos = 0.0;
        Double countNeg = 0.0;
        Double informationGain = 0.0;
        Double entropy = 0.0;
        Map<String, Double> data = new HashMap();
        for (CvFiltering filtering : cvFilteringlists) {
            if (filtering.getSkill().equals(skill) && filtering.getQualified()) {
                countPos++;
            } else if(filtering.getSkill().equals(skill) && !filtering.getQualified()){
                countNeg++;
            }
        }
        informationGain = (-countPos / (countPos + countNeg)) * (Math.log((countPos / (countPos + countNeg))) / Math.log(2)) - (countNeg / (countPos + countNeg)) * (Math.log((countNeg / (countPos + countNeg))) / Math.log(2));
        entropy = ((countPos + countNeg) / (this.countOverAllPos + this.countOverAllNeg)) * informationGain;
        data.put("informationGain", informationGain);
        data.put("entropy", entropy);
        return data;
    }

    Map<String, Double> getInformationGainEntropyForInteractivity(List<CvFiltering> cvFilteringlists, String interactivity) {
        Double countPos = 0.0;
        Double countNeg = 0.0;
        Double informationGain = 0.0;
        Double entropy = 0.0;
        Map<String, Double> data = new HashMap();
        for (CvFiltering filtering : cvFilteringlists) {
            if (filtering.getInteractivity().equals(interactivity) && filtering.getQualified()) {
                countPos++;
            } else if(filtering.getInteractivity().equals(interactivity) && !filtering.getQualified()){
                countNeg++;
            }
        }
        informationGain = (-countPos / (countPos + countNeg)) * (Math.log((countPos / (countPos + countNeg))) / Math.log(2)) - (countNeg / (countPos + countNeg)) * (Math.log((countNeg / (countPos + countNeg))) / Math.log(2));
        entropy = ((countPos + countNeg) / (this.countOverAllPos + this.countOverAllNeg)) * informationGain;
        data.put("informationGain", informationGain);
        data.put("entropy", entropy);
        return data;
    }

    @Override
    public void decisionTreeAlgorithm(InputDataDTO inputDataDTO) {

        List<String> jobPost = inputDataDTO.getJobPost();

        List<CvFiltering> cvFilteringlists = cvFilteringRepository.findAll();

        Double developerInformationGain = 0.0;
        Double qaInformationGain = 0.0;
        Double dbaInformationGain = 0.0;

        Double developerEntropy = 0.0;
        Double qaEntropy = 0.0;
        Double dbaEntropy = 0.0;

        Double jobPostEntropy = 0.0;
        Double jobPostGain;

        this.OverAllInformationGain = getInformationGain(cvFilteringlists);
        developerInformationGain = getInformationGainEntropyForJobPost(cvFilteringlists,"Developer").get("informationGain");
        developerEntropy = getInformationGainEntropyForJobPost(cvFilteringlists,"Developer").get("entropy");

        qaInformationGain = getInformationGainEntropyForJobPost(cvFilteringlists,"Q.A").get("informationGain");
        qaEntropy =getInformationGainEntropyForJobPost(cvFilteringlists,"Q.A").get("entropy");

        dbaInformationGain = getInformationGainEntropyForJobPost(cvFilteringlists,"DBA").get("informationGain");
        dbaEntropy = getInformationGainEntropyForJobPost(cvFilteringlists,"DBA").get("entropy");

        jobPostEntropy = developerEntropy + qaEntropy + dbaEntropy;
        jobPostGain = this.OverAllInformationGain - jobPostEntropy;
        System.out.println(jobPostGain);


        Double masterInformationGain = 0.0;
        Double bachelorInformationGain = 0.0;

        Double masterEntropy = 0.0;
        Double bachelorEntropy = 0.0;

        Double qualificationEntropy = 0.0;
        Double qualificationGain = 0.0;

        masterInformationGain = getInformationGainEntropyForQualification(cvFilteringlists,"Master").get("informationGain");
        masterEntropy = getInformationGainEntropyForQualification(cvFilteringlists,"Master").get("entropy");

        bachelorInformationGain =getInformationGainEntropyForQualification(cvFilteringlists,"Bachelor").get("informationGain");
        bachelorEntropy = getInformationGainEntropyForQualification(cvFilteringlists,"Bachelor").get("entropy");

        qualificationEntropy = masterEntropy + bachelorEntropy;
        qualificationGain = this.OverAllInformationGain - qualificationEntropy;
        System.out.println(qualificationGain);



        Double zeroInformationGain = 0.0;
        Double oneInformationGain = 0.0;
        Double twoInformationGain = 0.0;

        Double zeroEntropy = 0.0;
        Double oneEntropy = 0.0;
        Double twoEntropy = 0.0;

        Double experienceEntropy = 0.0;
        Double experienceGain = 0.0;


        zeroInformationGain =getInformationGainEntropyForExperience(cvFilteringlists,0).get("informationGain");
        zeroEntropy =getInformationGainEntropyForExperience(cvFilteringlists,0).get("entropy");

        oneInformationGain =getInformationGainEntropyForExperience(cvFilteringlists,1).get("informationGain");
        zeroEntropy =getInformationGainEntropyForExperience(cvFilteringlists,1).get("entropy");

        twoInformationGain =getInformationGainEntropyForExperience(cvFilteringlists,2).get("informationGain");
        twoEntropy =getInformationGainEntropyForExperience(cvFilteringlists,2).get("entropy");

        experienceEntropy = zeroEntropy + oneEntropy + twoEntropy;
        experienceGain = this.OverAllInformationGain - experienceEntropy;



        Double highInformationGain = 0.0;
        Double mediumInformationGain = 0.0;
        Double lowInformationGain = 0.0;

        Double highEntropy = 0.0;
        Double mediumEntropy = 0.0;
        Double lowEntropy = 0.0;

        Double skillEntropy = 0.0;
        Double skillGain = 0.0;

        highInformationGain =getInformationGainEntropyForSkill(cvFilteringlists,"High").get("informationGain");
        highEntropy =getInformationGainEntropyForSkill(cvFilteringlists,"High").get("entropy");

        mediumInformationGain =getInformationGainEntropyForSkill(cvFilteringlists,"Medium").get("informationGain");
        mediumEntropy =getInformationGainEntropyForSkill(cvFilteringlists,"Medium").get("entropy");

        lowInformationGain =getInformationGainEntropyForSkill(cvFilteringlists,"Low").get("informationGain");
        lowEntropy =getInformationGainEntropyForSkill(cvFilteringlists,"Low").get("entropy");

        skillEntropy = highEntropy + mediumEntropy + lowEntropy;
        skillGain = this.OverAllInformationGain - skillEntropy;


        Double highInteractivityInformationGain = 0.0;
        Double mediumInteractivityInformationGain = 0.0;
        Double lowInteractivityInformationGain = 0.0;

        Double highInteractivityEntropy = 0.0;
        Double mediumInteractivityEntropy = 0.0;
        Double lowInteractivityEntropy = 0.0;

        Double interactivityEntropy = 0.0;
        Double interactivityGain = 0.0;

        highInteractivityInformationGain =getInformationGainEntropyForInteractivity(cvFilteringlists,"High").get("informationGain");
        highInteractivityEntropy =getInformationGainEntropyForInteractivity(cvFilteringlists,"High").get("entropy");

        mediumInteractivityInformationGain =getInformationGainEntropyForInteractivity(cvFilteringlists,"Medium").get("informationGain");
        mediumInteractivityEntropy =getInformationGainEntropyForInteractivity(cvFilteringlists,"Medium").get("entropy");

        lowInteractivityInformationGain =getInformationGainEntropyForInteractivity(cvFilteringlists,"Low").get("informationGain");
        lowInteractivityEntropy =getInformationGainEntropyForInteractivity(cvFilteringlists,"Low").get("entropy");

        interactivityEntropy = highInteractivityEntropy + mediumInteractivityEntropy
                + lowInteractivityEntropy;
        interactivityGain = this.OverAllInformationGain - interactivityEntropy;



         Double maximum=Math.max(jobPostGain,Math.max(qualificationGain,Math.max(experienceGain,Math.max(skillGain,interactivityGain))));

        System.out.println("Root node is :"+maximum);


        String currentRootNode=" ";
        if(qualificationGain.equals(maximum)){
            root.add("Qualification");
            currentRootNode="Qualification";
        }else if(experienceGain.equals(maximum)){
            root.add("Experience");
            currentRootNode="Experience";
        }else if(jobPostGain.equals(maximum)){
            root.add("Jobpost");
            currentRootNode="Jobpost";
        } else if(skillGain.equals(maximum)){
            root.add("Skill");
            currentRootNode="Skill";
        }else if(interactivityGain.equals(maximum)){
            root.add("Interactivity");
            currentRootNode="Interactivity";
        }

        System.out.println(root.toString());

//        if(currentRootNode == "experience"){
//            List<String> experienceList = cvFilteringRepository.getUniqueColumnItems("experience");
//            experienceList.forEach((String experience) ->{
//                CvFiltering cvFilteringExperience = cvFilteringRepository.getByExperience(experience);
//                 //function here
//            });
//
//        }
//


//        cvFilteringRepository.getByExperience();


    }


}
