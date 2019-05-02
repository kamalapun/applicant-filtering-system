package com.cvfiltering.project.cvfiltering.controller;

import com.cvfiltering.project.cvfiltering.entity.Duration;
import com.cvfiltering.project.cvfiltering.service.DurationService;
import com.cvfiltering.project.cvfiltering.service.Implement.DurationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/duration")
public class DurationController {
    private DurationService durationService;

    public DurationController(DurationServiceImpl durationService) {
        this.durationService = durationService;
    }
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResponseEntity<?> insert(@RequestBody Duration duration){
        Duration duration1 = durationService.insert(duration);
        return new ResponseEntity<>(duration1, HttpStatus.OK);
    }
}
