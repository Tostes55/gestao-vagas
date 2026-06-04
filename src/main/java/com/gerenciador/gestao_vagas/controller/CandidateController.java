package com.gerenciador.gestao_vagas.controller;

import com.gerenciador.gestao_vagas.dto.ProfileCandidateResponseDTO;
import com.gerenciador.gestao_vagas.model.ApplyJobEntity;
import com.gerenciador.gestao_vagas.model.CandidateEntity;
import com.gerenciador.gestao_vagas.model.JobEntity;
import com.gerenciador.gestao_vagas.service.CandidateService;
import com.gerenciador.gestao_vagas.service.JobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/candidate")
@Tag(name = "Candidato", description = "Informações do candidato")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private JobService jobService;

    @PostMapping("/")
    @Operation(summary = "Cadastro do candidato", description = "Essa função é responsável por cadastrar um candidato")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = ProfileCandidateResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Usuário já existe")})
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {

        try {
            var result = this.candidateService.createCandidate(candidateEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Perfil do candidato", description = "Essa função é responsável por buscar as informações do perfil do candidato")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = ProfileCandidateResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = "User not found")})
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> get(HttpServletRequest request) {

        var idCandidate = request.getAttribute("candidate_id");
        try {
            var profile = this.candidateService.ProfileCandidate(UUID.fromString(idCandidate.toString()));
            return ResponseEntity.ok().body(profile);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/job")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Listagem de vagas disponíveis para o candidato", description = "Essa função é responsável por listar todas as vagas dispníveis, baseada no filtro")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = JobEntity.class)))})})
    @SecurityRequirement(name = "jwt_auth")
    public List<JobEntity> findAllJobsByFilter(String filter) {
        return  this.jobService.ListAllJobsByFilter(filter);
    }

    @PostMapping("/job/apply")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Inscricao do candidato para uma vaga", description = "Essa funcao e responsavel por fazer a inscricao de um candidado em uma vaga")
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> applyJob(HttpServletRequest request, @RequestBody UUID idJob) {
        var idCandidate = request.getAttribute("candidate_id");

        try{
            var result = this.candidateService.applyJobCandidate(UUID.fromString(idCandidate.toString()), idJob);
            return ResponseEntity.ok().body(result);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }



    }
}