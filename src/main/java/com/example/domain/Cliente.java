package com.example.domain;

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Cliente {

    @Id
    private String id;

    @NotBlank(message = "Nome é obrigatório!")
    private String nome;

    @NotBlank(message = "Deve ser informado ao menos um perfil")
    private List<String> roles;

    @NotBlank(message = "Deve ser informada a data de nasimento")
    private Long nasc;

    @NotBlank(message = "A data de ingresso deve ser informada")
    private LocalDate entrou;

}