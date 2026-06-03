package com.gerenciador.gestao_vagas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ProfileCandidateResponseDTO {

    @Schema(example = "Desenvolvedor Java")
    private String description;

    @Schema(example = "joao_silva")
    private String username;

    @Schema(example = "joao_silva@gmail.com")
    private String email;

    private UUID id;

    @Schema(example = "Joao Silva")
    private String name;
}
