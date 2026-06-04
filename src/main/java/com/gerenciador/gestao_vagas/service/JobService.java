package com.gerenciador.gestao_vagas.service;

import com.gerenciador.gestao_vagas.exceptions.CompanyNotFoundException;
import com.gerenciador.gestao_vagas.model.JobEntity;
import com.gerenciador.gestao_vagas.repository.CompanyRepository;
import com.gerenciador.gestao_vagas.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public JobEntity createJob(JobEntity jobEntity) {
        companyRepository.findById(jobEntity.getCompanyId()).orElseThrow(()->{
            throw new CompanyNotFoundException();
        });

        return this.jobRepository.save(jobEntity);

    }

    public List<JobEntity> ListAllJobsByFilter(String filter) {

        return this.jobRepository.findByDescriptionContainingIgnoreCase(filter);

    }
}
