package com.gerenciador.gestao_vagas.service;

import com.gerenciador.gestao_vagas.exceptions.UserFoundException;
import com.gerenciador.gestao_vagas.model.CompanyEntity;
import com.gerenciador.gestao_vagas.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity createCompany(CompanyEntity companyEntity) {
        this.companyRepository
                .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent((user)->{
                    throw new UserFoundException();
        });
        return this.companyRepository.save(companyEntity);
    }

}
