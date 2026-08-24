package br.com.ticket_api.dto.request;

import br.com.ticket_api.enums.Categoria;
import br.com.ticket_api.enums.Prioridade;

public record ChamadoRequestDTO (
        String titulo,
        String descricao,
        String cliente,
        Categoria categoria,
        Prioridade prioridade
){}
