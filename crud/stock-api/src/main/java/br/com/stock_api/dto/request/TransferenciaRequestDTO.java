package br.com.stock_api.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record TransferenciaRequestDTO(
        @NotNull(message = "O ID do produto de origem é obrigatório.")
        Long produtoOrigemId,

        @NotNull(message = "O ID do produto de destino é obrigatório.")
        Long produtoDestinoId,

        @NotNull(message = "A quantidade é obrigatória.")
        @Min(value = 1, message = "A quantidade transferida deve ser maior que zero.")
        Integer quantidade
) {
}
