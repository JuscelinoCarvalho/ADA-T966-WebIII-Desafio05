package com.example.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

//@EnableWebFlux
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
@Builder
public class Cliente {

    @Id
    private String id;

    @NotBlank(message = "Nome é obrigatório!")
    @NotNull(message = "Nome é obrigatório!")
    private String nome;

    @NotEmpty(message = "Deve ser informado ao menos um perfil")
    private List<String> roles;

    private Long nasc;

    //@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "A Data de ingresse não é válida!")
    @NotNull(message = "A data de ingresso deve ser informada")
    private LocalDate entrou;

}