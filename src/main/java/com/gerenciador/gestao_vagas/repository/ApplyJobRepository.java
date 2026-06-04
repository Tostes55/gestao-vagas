package com.gerenciador.gestao_vagas.repository;

import com.gerenciador.gestao_vagas.model.ApplyJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplyJobRepository extends JpaRepository <ApplyJobEntity, UUID> {
}
