package com.gerenciador.gestao_vagas.controller;

import com.gerenciador.gestao_vagas.model.CompanyEntity;
import com.gerenciador.gestao_vagas.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {
        @Autowired
        private CompanyService companyService;

        @PostMapping("/")
        public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity companyEntity) {
        try {
           var resultCompany =  this.companyService.createCompany(companyEntity);
           return ResponseEntity.ok().body(resultCompany);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
