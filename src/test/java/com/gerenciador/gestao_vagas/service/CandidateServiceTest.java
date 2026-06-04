package com.gerenciador.gestao_vagas.service;


import com.gerenciador.gestao_vagas.exceptions.JobNotFoundException;
import com.gerenciador.gestao_vagas.exceptions.UserNotFoundException;
import com.gerenciador.gestao_vagas.model.ApplyJobEntity;
import com.gerenciador.gestao_vagas.model.CandidateEntity;
import com.gerenciador.gestao_vagas.model.JobEntity;
import com.gerenciador.gestao_vagas.repository.ApplyJobRepository;
import com.gerenciador.gestao_vagas.repository.CandidateRepository;
import com.gerenciador.gestao_vagas.repository.JobRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//Notation pra habilitar o mock da injecao de dependencia
@ExtendWith(MockitoExtension.class)
public class CandidateServiceTest {

    //Notation do mockito substituta do @Autowired para os testes unitarios
    @InjectMocks
    private CandidateService candidateService;

    //Notation pra indicar que este repository mockado estaria vinculado ao @InjectMocks do service
    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;

    @Test
    @DisplayName("Should not be able to apply job with candidate not found")
    public void ShouldNotBeAbleToApplyJobWithCandidateNotFound(){

        try{
            candidateService.applyJobCandidate(null, null);
        } catch (Exception e){
            assertThat(e).isInstanceOf(UserNotFoundException.class);
        }
    }

    @Test
    public void ShouldNotBeAbleToApplyJobWithJobNotFound(){

        var idCandidate = UUID.randomUUID();

        var candidate = new CandidateEntity();
        candidate.setId(idCandidate);

        when(candidateRepository.findById(idCandidate))
                    .thenReturn(Optional.of(candidate));
        try{
            candidateService.applyJobCandidate(idCandidate, null);
        } catch (Exception e){
            assertThat(e).isInstanceOf(JobNotFoundException.class);
        }

    }

    @Test
    public void ShouldBeAbleToCreateANewApplyJob(){
        var idCandidate = UUID.randomUUID();
        var idJob = UUID.randomUUID();

        var applyJob = ApplyJobEntity.builder()
                .candidateId(idCandidate)
                .jobId(idJob).build();

        var ApplyJobCreated = ApplyJobEntity.builder().id(UUID.randomUUID()).build();

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(new CandidateEntity()));
        when(jobRepository.findById(idJob)).thenReturn(Optional.of(new JobEntity()));

        when(applyJobRepository.save(applyJob)).thenReturn(ApplyJobCreated);

        var result = candidateService.applyJobCandidate(idCandidate, idJob);

        //Valida se tem a propriedade ID
        assertThat(result).hasFieldOrProperty("id");

        //Valida se o id nao ficou nulo
        assertNotNull(result.getId());
    }

}
