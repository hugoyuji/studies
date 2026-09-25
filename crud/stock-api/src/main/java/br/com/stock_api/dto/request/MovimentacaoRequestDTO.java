package br.com.stock_api.dto.request;

import br.com.stock_api.enums.TipoMovimentacao;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record MovimentacaoRequestDTO(
        @NotNull(message = "O ID do produto é obrigatório.")
        Long produtoId,

        @NotNull(message = "A quantidade é obrigatória.")
        @Min(value = 1, message = "A quantidade movimentada deve ser maior que zero.")
        Integer quantidade,

        @NotNull(message = "O tipo de movimentação é obrigatório.")
        TipoMovimentacao tipo
) {
}
