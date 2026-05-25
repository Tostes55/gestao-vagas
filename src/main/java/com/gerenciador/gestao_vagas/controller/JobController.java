package com.gerenciador.gestao_vagas.controller;

import com.gerenciador.gestao_vagas.dto.JobRequestDTO;
import com.gerenciador.gestao_vagas.model.JobEntity;
import com.gerenciador.gestao_vagas.service.JobService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping("/")
    public JobEntity create (@Valid @RequestBody JobRequestDTO jobRequestDTO, HttpServletRequest request) {
        var companyId = request.getAttribute("company_id");

        var jobEntity = JobEntity.builder()
                .benefits(jobRequestDTO.getBenefits())
                .companyId(UUID.fromString(companyId.toString()))
                .description(jobRequestDTO.getDescription())
                .level(jobRequestDTO.getLevel())
                .build();

        return this.jobService.criarJob(jobEntity);

    }
}
