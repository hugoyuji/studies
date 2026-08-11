package br.com.gym_api.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AlunoResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private LocalDate dataNascimento;
    private LocalDate dataMatricula;
    private boolean ativo;
}
