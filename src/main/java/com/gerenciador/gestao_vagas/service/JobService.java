package com.gerenciador.gestao_vagas.service;

import com.gerenciador.gestao_vagas.model.JobEntity;
import com.gerenciador.gestao_vagas.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    public JobEntity criarJob(JobEntity jobEntity) {

        return this.jobRepository.save(jobEntity);

    }
}
