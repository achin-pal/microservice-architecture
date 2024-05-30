package com.palx.JobPortal.company.ropo;

import com.palx.JobPortal.company.pojo.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
}
