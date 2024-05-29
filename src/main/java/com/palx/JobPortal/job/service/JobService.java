package com.palx.JobPortal.job.service;

import com.palx.JobPortal.job.pojo.Job;

import java.util.List;

public interface JobService {

    List<Job> findAll();
    String createJoB(Job job);
    Job getJobById(Long id);
    Boolean deleteByJobId(Long id);

    Boolean updateJob(Long id, Job updatedJob);
}
