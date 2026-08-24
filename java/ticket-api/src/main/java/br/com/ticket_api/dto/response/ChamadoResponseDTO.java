package br.com.ticket_api.dto.response;

import br.com.ticket_api.enums.Categoria;
import br.com.ticket_api.enums.Prioridade;
import java.time.LocalDateTime;

public record ChamadoResponseDTO (

        Long id,
        String titulo,
        String descricao,
        String cliente,
        Categoria categoria,
        Prioridade prioridade,
        LocalDateTime dataHora,
        boolean concluido
) {}
