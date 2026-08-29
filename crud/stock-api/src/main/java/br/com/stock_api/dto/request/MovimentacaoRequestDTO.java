package br.com.stock_api.dto.request;

import br.com.stock_api.enums.TipoMovimentacao;

public record MovimentacaoRequestDTO(
        Long produtoId,
        Integer quantidade,
        TipoMovimentacao tipo
) {
}
