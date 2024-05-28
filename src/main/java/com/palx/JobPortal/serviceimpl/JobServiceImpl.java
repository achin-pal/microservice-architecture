package com.palx.JobPortal.serviceimpl;

import com.palx.JobPortal.pojo.Job;
import com.palx.JobPortal.service.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
    private List<Job>  jobs = new ArrayList<>();
    private Long nextId= 1L;
    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public String createJoB(Job job) {
        job.setId(nextId++);
        jobs.add(job);
        return "job added";
    }

    @Override
    public Job getJobById(Long id) {
        for (Job job : jobs){
            if(job.getId().equals(id)){
                return job;
            }
        }
        return null;
    }

    @Override
    public Boolean deleteByJobId(Long id) {   // for removing you can use iterator]
        Iterator<Job> jobIterator= jobs.iterator();
        while(jobIterator.hasNext()){
             Job job = jobIterator.next();
            if(job.getId().equals(id)){
                jobIterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean updateJob(Long id, Job updatedJob) {
        Iterator<Job> jobIterator= jobs.iterator();
        while(jobIterator.hasNext()){
            Job job = jobIterator.next();
            if(job.getId().equals(id)){
                job.setTitle(updatedJob.getTitle());
                job.setDescription(updatedJob.getDescription());
                job.setMinSalary(updatedJob.getMinSalary());
                job.setMaxSalary(updatedJob.getMaxSalary());
                return true;
            }
        }
        return false;
    }

}
