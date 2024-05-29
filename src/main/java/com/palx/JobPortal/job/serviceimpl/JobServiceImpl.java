package com.palx.JobPortal.job.serviceimpl;

import com.palx.JobPortal.job.pojo.Job;
import com.palx.JobPortal.job.repo.JobRepository;
import com.palx.JobPortal.job.service.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
//    private List<Job>  jobs = new ArrayList<>();
    JobRepository jobRepository;
    private Long nextId= 1L;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }


    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public String createJoB(Job job) {
        job.setId(nextId++);
        jobRepository.save(job);
        return "job added";
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
//        for (Job job : jobRepository.findById(id)){
//            if(job.getId().equals(id)){
//                return job;
//            }
//        }
//        return null;
    }

    @Override
    public Boolean deleteByJobId(Long id) {   // for removing you can use iterator]
   try {
       jobRepository.deleteById(id);
       return true;
       }catch (Exception e){
       return false;
   }

//        Iterator<Job> jobIterator= jobs.iterator();
//        while(jobIterator.hasNext()){
//             Job job = jobIterator.next();
//            if(job.getId().equals(id)){
//                jobIterator.remove();
//                return true;
//            }
//        }
//        return false;
    }

    @Override
    public Boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> optionalJob = jobRepository.findById(id);

//        Iterator<Job> jobIterator= jobs.iterator();
//        while(jobIterator.hasNext()){
//            Job job = jobIterator.next();
            if(optionalJob.isPresent()){
                Job job=optionalJob.get();
                job.setTitle(updatedJob.getTitle());
                job.setDescription(updatedJob.getDescription());
                job.setMinSalary(updatedJob.getMinSalary());
                job.setMaxSalary(updatedJob.getMaxSalary());
                jobRepository.save(job);
                return true;
            }
        return false;

    }

}
