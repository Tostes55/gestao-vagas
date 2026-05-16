package com.gerenciador.gestao_vagas.modules.candidate.repository;


import com.gerenciador.gestao_vagas.modules.candidate.model.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID>{

    Optional<CandidateEntity> findByUsernameOrEmail(String username, String email);

}
