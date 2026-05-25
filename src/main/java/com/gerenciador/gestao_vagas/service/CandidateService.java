package com.gerenciador.gestao_vagas.service;

import com.gerenciador.gestao_vagas.dto.ProfileCandidateResponseDTO;
import com.gerenciador.gestao_vagas.exceptions.UserFoundException;
import com.gerenciador.gestao_vagas.model.CandidateEntity;
import com.gerenciador.gestao_vagas.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CandidateEntity createCandidate(CandidateEntity candidateEntity) {
        this.candidateRepository
                .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
                .ifPresent((user)-> {
                    throw new UserFoundException();
                });

        var password = passwordEncoder.encode(candidateEntity.getPassword());
        candidateEntity.setPassword(password);

        return this.candidateRepository.save(candidateEntity);
    }

    public ProfileCandidateResponseDTO ProfileCandidate(UUID idCandidate) {

        var candidate = this.candidateRepository.findById(idCandidate)
                .orElseThrow(()->{
                    throw new UsernameNotFoundException("User not found");
                });

        var candidateDTO = ProfileCandidateResponseDTO.builder()
                .description(candidate.getDescription())
                .username(candidate.getUsername())
                .email(candidate.getEmail())
                .name(candidate.getName())
                .id(candidate.getId())
                .build();
        return candidateDTO;

    }

}
