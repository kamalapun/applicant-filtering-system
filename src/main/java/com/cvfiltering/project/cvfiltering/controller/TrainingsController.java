package com.cvfiltering.project.cvfiltering.controller;

import com.cvfiltering.project.cvfiltering.entity.Trainings;
import com.cvfiltering.project.cvfiltering.service.Implement.TrainingsServiceImpl;
import com.cvfiltering.project.cvfiltering.service.TrainingsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/trainings")
public class TrainingsController {
    private TrainingsService trainingsService;

    public TrainingsController(TrainingsServiceImpl trainingsService) {
        this.trainingsService = trainingsService;
    }
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResponseEntity<?> insert(@RequestBody Trainings trainings){
        Trainings trainings1 = trainingsService.insert(trainings);
        return new ResponseEntity<>(trainings1, HttpStatus.OK);
    }
}
