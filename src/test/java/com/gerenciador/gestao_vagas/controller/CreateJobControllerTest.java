package com.gerenciador.gestao_vagas.controller;


import com.gerenciador.gestao_vagas.dto.JobRequestDTO;
import com.gerenciador.gestao_vagas.exceptions.CompanyNotFoundException;
import com.gerenciador.gestao_vagas.model.CompanyEntity;
import com.gerenciador.gestao_vagas.repository.CompanyRepository;
import com.gerenciador.gestao_vagas.utils.TestUtils;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateJobControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CompanyRepository  companyRepository;

    @BeforeEach
    public void setup(){
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void ShouldBeAbleToCreateANewJob() throws Exception {

        var company = CompanyEntity.builder()
                .description("COMPANY_DESCRIPTION")
                .email("COMPANY_EMAIL@email.com")
                .password("123412341234")
                .username("COMPANY_USERNAME")
                .name("COMPANY_NAME")
                .build();

        companyRepository.saveAndFlush(company);

        var jobRequestDTO = JobRequestDTO.builder()
                .benefits("Benefits Test")
                .description("Description Test")
                .level("Level Test")
                .build();

        var result = mvc.perform(MockMvcRequestBuilders.post("/company/job/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.objectToJson(jobRequestDTO))
                        .header("Authorization",
                                TestUtils.generateToken(company.getId(), "JAVAGAS_@123#")))
                .andExpect(MockMvcResultMatchers.status().isOk());


    }

    @Test
    public void ShouldNotBeAbleToCreateANewJobIfCompanyNotFound() throws Exception {

        var jobRequestDTO = JobRequestDTO.builder()
                .benefits("Benefits Test")
                .description("Description Test")
                .level("Level Test")
                .build();

            mvc.perform(MockMvcRequestBuilders.post("/company/job/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtils.objectToJson(jobRequestDTO))
                    .header("Authorization",
                            TestUtils.generateToken(UUID.randomUUID(), "JAVAGAS_@123#")))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());


    }



}
