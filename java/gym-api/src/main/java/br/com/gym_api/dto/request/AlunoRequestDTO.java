package br.com.gym_api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AlunoRequestDTO {

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String telefone;

    @NotNull
    private LocalDate dataNascimento;

    @NotNull
    private LocalDate dataMatricula;

    @NotNull
    private Boolean ativo;
}
