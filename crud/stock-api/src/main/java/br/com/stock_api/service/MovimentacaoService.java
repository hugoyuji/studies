package br.com.stock_api.service;

import br.com.stock_api.dto.request.MovimentacaoRequestDTO;
import br.com.stock_api.dto.request.TransferenciaRequestDTO;
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
                .orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado."));

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

    @Transactional
    public void realizarTransferencia(TransferenciaRequestDTO dto){
        Produto origem = produtoRepository.findById(dto.produtoOrigemId())
                .orElseThrow(() -> new ProdutoNotFoundException("Produto de origem não encontrado."));

        Produto destino = produtoRepository.findById(dto.produtoDestinoId())
                .orElseThrow(() -> new ProdutoNotFoundException("Produto de destino não encontrado."));

        if(origem.getQuantidade() < dto.quantidade()){
            throw new EstoqueInsuficienteException("Estoque insuficiente no produto de origem para realizar a transferência.");
        }

        origem.setQuantidade(origem.getQuantidade() - dto.quantidade());
        destino.setQuantidade(destino.getQuantidade() + dto.quantidade());

        produtoRepository.save(origem);
        produtoRepository.save(destino);

        Movimentacao saida = new Movimentacao(origem, dto.quantidade(), LocalDateTime.now(), TipoMovimentacao.SAIDA);
        Movimentacao entrada = new Movimentacao(destino, dto.quantidade(), LocalDateTime.now(), TipoMovimentacao.ENTRADA);

        movimentacaoRepository.save(saida);
        movimentacaoRepository.save(entrada);
    }
}