package com.cvfiltering.project.cvfiltering.controller;

import com.cvfiltering.project.cvfiltering.entity.Company;
import com.cvfiltering.project.cvfiltering.service.CompanyService;
import com.cvfiltering.project.cvfiltering.service.Implement.CompanyServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(value = "/api/company")
public class CompanyController {
    private CompanyService companyService;

    public CompanyController(CompanyServiceImpl companyService) {
        this.companyService = companyService;
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResponseEntity<?> insert(@RequestBody Company company){
        Company company1 = companyService.insert(company);
        return new ResponseEntity<Company>(company1, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Company company, @PathVariable Long id) {
        companyService.update(company,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
       Company company1 = companyService.getOne(id);
        return new ResponseEntity<Company>(company1, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        companyService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAll(){
        return new ResponseEntity<List<Company>>(companyService.getAll(),HttpStatus.OK);

    }
}
