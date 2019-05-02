package com.cvfiltering.project.cvfiltering.controller;

import com.cvfiltering.project.cvfiltering.entity.JobInfo;
import com.cvfiltering.project.cvfiltering.service.Implement.JobInfoServiceImpl;
import com.cvfiltering.project.cvfiltering.service.JobInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/jobinfo")
public class JobInfoController {
    private JobInfoService jobInfoService;

    public JobInfoController(JobInfoServiceImpl jobInfoService) {
        this.jobInfoService =jobInfoService ;
    }
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResponseEntity<?> insert(@RequestBody JobInfo jobInfo){
        JobInfo jobInfo1 = jobInfoService.insert(jobInfo);
        return new ResponseEntity<>(jobInfo1, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody JobInfo jobInfo,@PathVariable Long id){
        jobInfoService.update(jobInfo,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        jobInfoService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        JobInfo jobInfo1 = jobInfoService.getOne(id);
        return new ResponseEntity<>(jobInfo1,HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<JobInfo>> getAll(){
        return new ResponseEntity<List<JobInfo>>(jobInfoService.getAll(),HttpStatus.OK);
    }
}
