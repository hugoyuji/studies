package br.com.stock_api.dto.response;

import br.com.stock_api.enums.TipoMovimentacao;

import java.time.LocalDateTime;

public record MovimentacaoResponseDTO(
        Long id,
        Long produtoId,
        Integer quantidade,
        LocalDateTime dataHora,
        TipoMovimentacao tipo

) {
}
