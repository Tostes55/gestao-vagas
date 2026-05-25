package com.gerenciador.gestao_vagas.controller;

import com.gerenciador.gestao_vagas.dto.AuthCompanyDTO;
import com.gerenciador.gestao_vagas.infra.security.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/auth")
public class AuthCompanyController {

    @Autowired
    private Auth auth;

    @PostMapping("/company")
    public ResponseEntity<Object> create(@RequestBody AuthCompanyDTO  authCompanyDTO) throws AuthenticationException {
        try{
           var result = this.auth.AuthCompany(authCompanyDTO);
           return ResponseEntity.ok().body(result);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}

