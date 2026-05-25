package com.gerenciador.gestao_vagas.repository;

import com.gerenciador.gestao_vagas.model.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {
    Optional<CompanyEntity> findByUsernameOrEmail(String userName, String email);
    Optional<CompanyEntity> findByUsername(String username);
}
