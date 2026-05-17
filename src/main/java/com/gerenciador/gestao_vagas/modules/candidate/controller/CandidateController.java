package com.gerenciador.gestao_vagas.modules.candidate.controller;

import com.gerenciador.gestao_vagas.modules.candidate.exceptions.UserFoundException;
import com.gerenciador.gestao_vagas.modules.candidate.model.CandidateEntity;
import com.gerenciador.gestao_vagas.modules.candidate.repository.CandidateRepository;
import com.gerenciador.gestao_vagas.modules.candidate.service.CandidateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {

        try {
            var result = this.candidateService.createCandidate(candidateEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
