package com.gerenciador.gestao_vagas.infra.security;

import com.auth0.jwt.algorithms.Algorithm;
import com.gerenciador.gestao_vagas.dto.AuthCandidateRequestDTO;
import com.gerenciador.gestao_vagas.dto.AuthCandidateResponseDTO;
import com.gerenciador.gestao_vagas.dto.AuthCompanyDTO;
import com.gerenciador.gestao_vagas.dto.AuthCompanyResponseDTO;
import com.gerenciador.gestao_vagas.repository.CandidateRepository;
import com.gerenciador.gestao_vagas.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;


@Service
public class Auth {

    @Value("${security.token.secret_company}")
    private String companySecretKey;

    @Value("${security.token.secret_candidate}")
    private String candidateSecretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CandidateRepository candidateRepository;

    public AuthCompanyResponseDTO AuthCompany(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {

        var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
                ()-> {
                    throw new UsernameNotFoundException("Company not found");
                });

        var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        if(!passwordMatches) {
            throw new AuthenticationException("Invalid password");
        }

        Algorithm algorithm = Algorithm.HMAC256(companySecretKey);

        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        var token = JWT.create().withIssuer("javagas")
                .withExpiresAt(expiresIn)
                .withSubject(company.getId().toString())
                .withClaim("roles",Arrays.asList("COMPANY"))
                .sign(algorithm);

        var authCompanyResponseDTO = AuthCompanyResponseDTO.builder()
                .acess_token(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();

        return authCompanyResponseDTO;
    }

    public AuthCandidateResponseDTO AuthCandidate(AuthCandidateRequestDTO authCandidateRequestDTO) throws AuthenticationException {

        var candidate = this.candidateRepository.findByUsername(authCandidateRequestDTO.username())
                .orElseThrow(()-> {
                    throw new UsernameNotFoundException("Username/Password incorrect");
                });

        var passwordsMatches = this.passwordEncoder
                .matches(authCandidateRequestDTO.password(), candidate.getPassword());

        if(!passwordsMatches) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(candidateSecretKey);

        var expiresIn = Instant.now().plus(Duration.ofMinutes(10));

        var token = JWT.create()
                .withIssuer("javagas")
                .withSubject(candidate.getId().toString())
                .withClaim("roles", Arrays.asList("CANDIDATE"))
                .withExpiresAt(expiresIn)
                .sign(algorithm);

        var authCandidateResponse = AuthCandidateResponseDTO.builder()
                .acess_token(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();
        return authCandidateResponse;
    }


}
