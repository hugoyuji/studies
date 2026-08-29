package br.com.stock_api.dto.request;

public record ProdutoRequestDTO(
        String nome,
        Integer quantidade
) {
}
