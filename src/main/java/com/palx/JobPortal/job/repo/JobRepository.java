package com.palx.JobPortal.job.repo;

import com.palx.JobPortal.job.pojo.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Long> {
}
