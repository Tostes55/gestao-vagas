package com.gerenciador.gestao_vagas.controller;

import com.gerenciador.gestao_vagas.model.JobEntity;
import com.gerenciador.gestao_vagas.service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping("/")
    public JobEntity create (@Valid @RequestBody JobEntity jobEntity) {
        return this.jobService.criarJob(jobEntity);

    }
}
