package br.com.stock_api.controller;

import br.com.stock_api.dto.request.ProdutoRequestDTO;
import br.com.stock_api.dto.response.ProdutoResponseDTO;
import br.com.stock_api.service.ProdutoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    public ProdutoResponseDTO cadastrar(@RequestBody ProdutoRequestDTO dto){
        return service.cadastrar(dto);
    }

    @GetMapping("/{id}")
    public ProdutoResponseDTO buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @GetMapping
    public List<ProdutoResponseDTO> listar(){
        return service.listar();
    }

    @PutMapping("/{id}")
    public ProdutoResponseDTO alterar(@RequestBody ProdutoRequestDTO dto, @PathVariable Long id){
        return service.alterar(dto, id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        service.deletar(id);
    }
}
