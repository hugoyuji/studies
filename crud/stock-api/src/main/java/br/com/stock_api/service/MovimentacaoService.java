package br.com.stock_api.service;

import br.com.stock_api.dto.request.MovimentacaoRequestDTO;
import br.com.stock_api.dto.response.MovimentacaoResponseDTO;
import br.com.stock_api.enums.TipoMovimentacao;
import br.com.stock_api.exception.EstoqueInsuficienteException;
import br.com.stock_api.exception.ProdutoNotFoundException;
import br.com.stock_api.model.Movimentacao;
import br.com.stock_api.model.Produto;
import br.com.stock_api.repository.MovimentacaoRepository;
import br.com.stock_api.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class MovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;
    private final ProdutoRepository produtoRepository;

    public MovimentacaoService(
            MovimentacaoRepository movimentacaoRepository,
            ProdutoRepository produtoRepository) {

        this.movimentacaoRepository = movimentacaoRepository;
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public MovimentacaoResponseDTO realizarMovimentacao(
            MovimentacaoRequestDTO dto) {

        Produto produto = produtoRepository.findById(dto.produtoId())
                .orElseThrow(() ->
                        new RuntimeException("Produto não encontrado.")
                );

        if (dto.tipo() == TipoMovimentacao.ENTRADA) {

            produto.setQuantidade(
                    produto.getQuantidade() + dto.quantidade()
            );

        } else if (dto.tipo() == TipoMovimentacao.SAIDA) {

            if (produto.getQuantidade() < dto.quantidade()) {
                throw new EstoqueInsuficienteException("Estoque insuficiente para realizar esta saída.");
            }

            produto.setQuantidade(
                    produto.getQuantidade() - dto.quantidade()
            );
        }

        produtoRepository.save(produto);

        Movimentacao movimentacao = new Movimentacao(
                produto,
                dto.quantidade(),
                LocalDateTime.now(),
                dto.tipo()
        );

        movimentacaoRepository.save(movimentacao);

        return new MovimentacaoResponseDTO(
                movimentacao.getId(),
                produto.getId(),
                movimentacao.getQuantidade(),
                movimentacao.getDataHora(),
                movimentacao.getTipo()
        );
    }
}