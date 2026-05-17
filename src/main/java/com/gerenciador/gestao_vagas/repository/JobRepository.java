package com.gerenciador.gestao_vagas.repository;

import com.gerenciador.gestao_vagas.model.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {
}
