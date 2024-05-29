package com.palx.JobPortal.job.controller;


import com.palx.JobPortal.job.pojo.Job;
import com.palx.JobPortal.job.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs(){
        List<Job>  job= jobService.findAll();
        if(job !=null){
            return  ResponseEntity.ok(job);
        }
        return new ResponseEntity<>(job, HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<String> createJobs( @RequestBody Job job){
        jobService.createJoB(job);
        return ResponseEntity.ok("jobs added");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJob(@PathVariable Long id) {

//        ResponseEntity responseEntity = new ResponseEntity<>();
        Job job = jobService.getJobById(id);
                  if(job !=null){
                      return  new ResponseEntity<>(job, HttpStatus.OK);
                  }
                      return new ResponseEntity<>(job, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob( @PathVariable Long id){
            Boolean del = jobService.deleteByJobId(id);
             if(del) {
                 return ResponseEntity.ok("deleted");
             }
             return new ResponseEntity<>("Not deleted", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob( @PathVariable Long id ,@RequestBody Job updatedJob){
        Boolean del = jobService.updateJob(id,updatedJob);
        if(del) {
            return ResponseEntity.ok("Updated");
        }
        return new ResponseEntity<>("Not updated", HttpStatus.NOT_FOUND);
    }
}
