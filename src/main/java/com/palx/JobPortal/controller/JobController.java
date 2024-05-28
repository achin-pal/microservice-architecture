package com.palx.JobPortal.controller;


import com.palx.JobPortal.pojo.Job;
import com.palx.JobPortal.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobsget")
    public ResponseEntity<List<Job>> getAllJobs(){
        List<Job>  job= jobService.findAll();
        if(job !=null){
            return  ResponseEntity.ok(job);
        }
        return new ResponseEntity<>(job, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/jobcreate")
    public ResponseEntity<String> createJobs( @RequestBody Job job){
        jobService.createJoB(job);
        return ResponseEntity.ok("jobs added");
    }

    @GetMapping("/jobget/{id}")
    public ResponseEntity<Job> getJob(@PathVariable Long id) {

//        ResponseEntity responseEntity = new ResponseEntity<>();
        Job job = jobService.getJobById(id);
                  if(job !=null){
                      return  new ResponseEntity<>(job, HttpStatus.OK);
                  }
                      return new ResponseEntity<>(job, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/jobdelete")
    public ResponseEntity<String> deleteJob( @PathVariable Long id){
            Boolean del = jobService.deleteByJobId(id);
             if(del) {
                 return ResponseEntity.ok("deleted");
             }
             return new ResponseEntity<>("Not deleted", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/jobupdate")
    public ResponseEntity<String> updateJob( @PathVariable Long id ,@RequestBody Job updatedJob){
        Boolean del = jobService.updateJob(id,updatedJob);
        if(del) {
            return ResponseEntity.ok("Updated");
        }
        return new ResponseEntity<>("Not updated", HttpStatus.NOT_FOUND);
    }
}
