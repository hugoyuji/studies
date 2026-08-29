package br.com.stock_api.service;

import br.com.stock_api.dto.request.ProdutoRequestDTO;
import br.com.stock_api.dto.response.ProdutoResponseDTO;
import br.com.stock_api.model.Produto;
import br.com.stock_api.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public ProdutoResponseDTO cadastrar(ProdutoRequestDTO dto){
        Produto produto = new Produto(
                null,
                dto.nome(),
                dto.quantidade()
        );

        Produto salvo = repository.save(produto);

        return new ProdutoResponseDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getQuantidade()
        );
    }

    public ProdutoResponseDTO buscarPorId(Long id){
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(),
                produto.getQuantidade()
        );
    }

    public List<ProdutoResponseDTO> listar(){
        List<Produto> produtos = repository.findAll();
        List<ProdutoResponseDTO> responses = new ArrayList<>();

        for (Produto produto : produtos){
            ProdutoResponseDTO response = new ProdutoResponseDTO(
                    produto.getId(),
                    produto.getNome(),
                    produto.getQuantidade()
            );
            responses.add(response);
        }
        return responses;
    }

    public ProdutoResponseDTO alterar(ProdutoRequestDTO dto, Long id){
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        produto.setNome(dto.nome());
        produto.setQuantidade(dto.quantidade());

        Produto produtoSalvo = repository.save(produto);

        ProdutoResponseDTO response = new ProdutoResponseDTO(
                produtoSalvo.getId(),
                produtoSalvo.getNome(),
                produtoSalvo.getQuantidade()
        );

        return response;
    }

    public void deletar(Long id){
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        repository.deleteById(id);
    }
}