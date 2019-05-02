package com.cvfiltering.project.cvfiltering.controller;

import com.cvfiltering.project.cvfiltering.entity.Experiences;
import com.cvfiltering.project.cvfiltering.service.ExperiencesService;
import com.cvfiltering.project.cvfiltering.service.Implement.ExperiencesServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/experiences")
public class ExperiencesController {
    private ExperiencesService experiencesService;

    public ExperiencesController(ExperiencesServiceImpl experiencesService) {
        this.experiencesService = experiencesService;
    }
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResponseEntity<?> insert(@RequestBody Experiences experiences){
        Experiences experiences1 = experiencesService.insert(experiences);
        return new ResponseEntity<>(experiences1, HttpStatus.OK);
    }
}
