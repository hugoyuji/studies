package br.com.stock_api.dto.response;

public record ProdutoResponseDTO(
        Long id,
        String nome,
        Integer quantidade
) {
}
