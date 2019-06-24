package com.cvfiltering.project.cvfiltering.controller;

import com.cvfiltering.project.cvfiltering.entity.InputDataDTO;
import com.cvfiltering.project.cvfiltering.service.AlgorithmicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/jobseeker")
public class CvFilteringController {
    private AlgorithmicService algorithmicService;

    public CvFilteringController(AlgorithmicService algorithmicService) {
        this.algorithmicService = algorithmicService;
    }

    @PostMapping("/dt")
    public ResponseEntity<?> decisionTreeAlgorithm(@RequestBody InputDataDTO inputDataDTO)throws IOException{
        System.out.println(inputDataDTO);
        String response = algorithmicService.algorithmicCompution(inputDataDTO);
        System.out.println(response);
        return new ResponseEntity<Object>(response, HttpStatus.OK);

    }
//
//    @GetMapping("/")
//    public ResponseEntity<?> getJobSeekeer() {
//
//    }

}
