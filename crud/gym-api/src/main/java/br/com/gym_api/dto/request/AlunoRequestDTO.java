package br.com.gym_api.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AlunoRequestDTO {

    private String nome;
    private String email;
    private String telefone;
    private LocalDate dataNascimento;
    private LocalDate dataMatricula;
    private boolean ativo;
}
