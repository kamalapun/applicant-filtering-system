package com.cvfiltering.project.cvfiltering.controller;


import com.cvfiltering.project.cvfiltering.entity.Languages;
import com.cvfiltering.project.cvfiltering.service.Implement.LanguagesServiceImpl;
import com.cvfiltering.project.cvfiltering.service.LanguagesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/language")
public class LanguagesController {
    private LanguagesService languagesService;

    public LanguagesController(LanguagesServiceImpl languagesService) {
        this.languagesService = languagesService;
    }
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResponseEntity<?> insert(@RequestBody Languages languages){
        Languages languages1 = languagesService.insert(languages);
        return new ResponseEntity<>(languages1, HttpStatus.OK);
    }
}
