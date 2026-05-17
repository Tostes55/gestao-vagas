package com.gerenciador.gestao_vagas.modules.candidate.service;

import com.gerenciador.gestao_vagas.modules.candidate.exceptions.UserFoundException;
import com.gerenciador.gestao_vagas.modules.candidate.model.CandidateEntity;
import com.gerenciador.gestao_vagas.modules.candidate.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity createCandidate(CandidateEntity candidateEntity) {
        this.candidateRepository
                .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
                .ifPresent((user)-> {
                    throw new UserFoundException();
                });

        return this.candidateRepository.save(candidateEntity);
    }

}
