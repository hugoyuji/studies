package br.com.stock_api.controller;

import br.com.stock_api.dto.request.ProdutoRequestDTO;
import br.com.stock_api.dto.response.ProdutoResponseDTO;
import br.com.stock_api.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Produtos", description = "Endpoints para gerenciamento de produtos no estoque.")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Cadastrar um novo produto", description = "Salva um novo produto no banco de dados e retorna os dados criados.")
    public ResponseEntity<ProdutoResponseDTO> cadastrar(@RequestBody @Valid ProdutoRequestDTO dto){
        ProdutoResponseDTO response = service.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar produto por ID", description = "Retorna os detalhes de um produto específico baseado no seu ID.")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping
    @Operation(summary = "Listar todos os produtos", description = "Retorna uma lista com todos os produtos cadastrados no estoque.")
    public ResponseEntity<List<ProdutoResponseDTO>> listar(){
        return ResponseEntity.ok(service.listar());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um produto", description = "Atualiza os dados (nome e quantidade) de um produto existente.")
    public ResponseEntity<ProdutoResponseDTO> alterar(@RequestBody @Valid ProdutoRequestDTO dto, @PathVariable Long id){
        return ResponseEntity.ok(service.alterar(dto, id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um produto", description = "Remove um produto do banco de dados pelo seu ID.")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
