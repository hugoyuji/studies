package br.com.stock_api.dto.request;

public record TransferenciaRequestDTO(
        Long produtoOrigemId,
        Long produtoDestinoId,
        Integer quantidade
) {
}
