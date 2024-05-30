package com.palx.JobPortal.company.controller;


import com.palx.JobPortal.company.pojo.Company;
import com.palx.JobPortal.company.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllcomapnies(){
        return new ResponseEntity<>(companyService.getAllCompanies(),
                HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> companyUpdate(@PathVariable Long id, @RequestBody Company company){
        companyService.updateCompany(company,id);
        return  new ResponseEntity<>("update", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> saveCompany(@RequestBody Company company){
        companyService.createCompany(company);
        return new ResponseEntity<>("company saved", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id){
       boolean isDeleted= companyService.deleteCompany(id);
       if(isDeleted){
              return new ResponseEntity<>("deleted" , HttpStatus.OK);
       }
        return new ResponseEntity<>("not deleted" , HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
        Company company =  companyService.getCompanyById(id);
        if(company !=null){
            return  new ResponseEntity<>(company,HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
