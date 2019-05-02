package com.cvfiltering.project.cvfiltering.controller;

import com.cvfiltering.project.cvfiltering.entity.Skills;
import com.cvfiltering.project.cvfiltering.service.Implement.SkillsServiceImpl;
import com.cvfiltering.project.cvfiltering.service.SkillsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/skills")
public class SkillsController {
    private SkillsService skillsService;

    public SkillsController(SkillsServiceImpl skillsService) {
        this.skillsService = skillsService;
    }
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResponseEntity<?> insert(@RequestBody Skills skills){
        Skills skills1 = skillsService.insert(skills);
        return new ResponseEntity<>(skills1, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Skills skills,@PathVariable Long id){
        skillsService.update(skills,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Skills>> getAllSkills(){
        return new ResponseEntity<List<Skills>>(skillsService.getAllSkills(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        Skills skills1 = skillsService.getOne(id);
        return new ResponseEntity<>(skills1,HttpStatus.OK);
    }

}
