package com.cvfiltering.project.cvfiltering.entity;

import lombok.Data;

import java.util.List;
public class InputDataDTO {
    List<String> jobPost;
    List<String> qualification;
    List<Integer> experience;
    List<String> skill;
    List<String> interactivity;


    public InputDataDTO() {
    }

    public InputDataDTO(List<String> jobPost, List<String> qualification, List<Integer> experience, List<String> skill, List<String> interactivity) {
        this.jobPost = jobPost;
        this.qualification = qualification;
        this.experience = experience;
        this.skill = skill;
        this.interactivity = interactivity;
    }

    public List<String> getJobPost() {
        return jobPost;
    }

    public List<String> getQualification() {
        return qualification;
    }

    public List<Integer> getExperience() {
        return experience;
    }

    public List<String> getSkill() {
        return skill;
    }

    public List<String> getInteractivity() {
        return interactivity;
    }

    public void setJobPost(List<String> jobPost) {
        this.jobPost = jobPost;
    }

    public void setQualification(List<String> qualification) {
        this.qualification = qualification;
    }

    public void setExperience(List<Integer> experience) {
        this.experience = experience;
    }

    public void setSkill(List<String> skill) {
        this.skill = skill;
    }

    public void setInteractivity(List<String> interactivity) {
        this.interactivity = interactivity;
    }

    @Override
    public String toString() {
        return "InputDataDTO{" +
                "jobPost=" + jobPost +
                ", qualification=" + qualification +
                ", experience=" + experience +
                ", skill=" + skill +
                ", interactivity=" + interactivity +
                '}';
    }

}
