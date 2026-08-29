package br.com.ticket_api.dto.request;

import br.com.ticket_api.enums.Categoria;
import br.com.ticket_api.enums.Prioridade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ChamadoRequestDTO (
        @NotBlank(message = "O título é obrigatório.")
        @Size(min = 3, max = 100, message = "O título deve ter entre 3 e 100 caracteres.")
        String titulo,

        @NotBlank(message = "A descrição é obrigatória.")
        String descricao,

        @NotBlank(message = "O nome do cliente é obrigatório.")
        String cliente,

        @NotNull(message = "A categoria é obrigatória.")
        Categoria categoria,

        @NotNull(message = "A prioridade é obrigatória.")
        Prioridade prioridade,

        Boolean concluido
){}
