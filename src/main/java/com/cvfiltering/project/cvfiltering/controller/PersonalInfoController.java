package com.cvfiltering.project.cvfiltering.controller;


import com.cvfiltering.project.cvfiltering.entity.PersonalInfo;
import com.cvfiltering.project.cvfiltering.service.Implement.PersonalInfoServiceImpl;
import com.cvfiltering.project.cvfiltering.service.PersonalInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/personalinfo")
public class PersonalInfoController {
    private PersonalInfoService personalInfoService;

    public PersonalInfoController(PersonalInfoServiceImpl personalInfoService) {
        this.personalInfoService = personalInfoService;
    }
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResponseEntity<?> insert(@RequestBody PersonalInfo personalInfo){
        PersonalInfo personalInfo1 = personalInfoService.insert(personalInfo);
        return new ResponseEntity<>(personalInfo1, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        PersonalInfo personalInfo1 = personalInfoService.getOne(id);
        return new ResponseEntity<>(personalInfo1,HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<PersonalInfo>> getAllName(){
        return new ResponseEntity<List<PersonalInfo>>(personalInfoService.getAll(),HttpStatus.OK);
    }
}
