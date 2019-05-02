package com.cvfiltering.project.cvfiltering.controller;


import com.cvfiltering.project.cvfiltering.entity.Education;
import com.cvfiltering.project.cvfiltering.service.EducationService;
import com.cvfiltering.project.cvfiltering.service.Implement.EducationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/education")
public class EducationController {
    private EducationService educationService;

    public EducationController(EducationServiceImpl educationService) {
        this.educationService = educationService;
    }
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResponseEntity<?> insert(@RequestBody Education education){
        Education education1 = educationService.insert(education);
        return new ResponseEntity<>(education1, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        Education education1 = educationService.getOne(id);
        return new ResponseEntity<>(education1,HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Education>> getAll(){
        return new ResponseEntity<List<Education>>(educationService.getAll(),HttpStatus.OK);
    }
}
