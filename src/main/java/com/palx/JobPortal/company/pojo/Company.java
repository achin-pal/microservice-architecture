package com.palx.JobPortal.company.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.palx.JobPortal.job.pojo.Job;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<Job> jobList;

    //private List<Review> reviews;

    public Company() {
    }

    public Company(Long id, String name, String description, List<Job> jobList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.jobList = jobList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Job> getJobList() {
        return jobList;
    }

    public void setJobList(List<Job> jobList) {
        this.jobList = jobList;
    }
}
