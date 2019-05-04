package com.cvfiltering.project.cvfiltering.controller;

import com.cvfiltering.project.cvfiltering.entity.CvFiltering;
import com.cvfiltering.project.cvfiltering.service.CvFilteringService;
import com.cvfiltering.project.cvfiltering.service.Implement.CvFilteringServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/jobseeker")
public class CvFilteringController {
    private CvFilteringService cvFilteringService;

    public CvFilteringController(CvFilteringServiceImpl cvFilteringService) {
        this.cvFilteringService = cvFilteringService;
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResponseEntity<?> insert(@RequestBody CvFiltering cvFiltering){
        CvFiltering cvFiltering1 = cvFilteringService.insert(cvFiltering);
        return new ResponseEntity<CvFiltering>(cvFiltering1, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        CvFiltering cvFiltering = cvFilteringService.getOne(id);
        return new ResponseEntity<CvFiltering>(cvFiltering, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CvFiltering>> getAll(){
        return new ResponseEntity<List<CvFiltering>>(cvFilteringService.getAll(),HttpStatus.OK);

    }
}
