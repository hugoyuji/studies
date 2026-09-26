
package br.com.stock_api.controller;

import br.com.stock_api.dto.request.MovimentacaoRequestDTO;
import br.com.stock_api.dto.request.TransferenciaRequestDTO;
import br.com.stock_api.dto.response.MovimentacaoResponseDTO;
import br.com.stock_api.service.MovimentacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movimentacoes")
@Tag(name = "Movimentações", description = "Endpoints para gerenciar entradas e saídas do estoque.")
public class MovimentacaoController {

    private final MovimentacaoService service;

    public MovimentacaoController(MovimentacaoService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Registrar movimentação", description = "Registra uma entrada ou saída de estoque para um produto específico.")
    public ResponseEntity<MovimentacaoResponseDTO> realizarMovimentacao(@RequestBody @Valid MovimentacaoRequestDTO dto){
        MovimentacaoResponseDTO response = service.realizarMovimentacao(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/transferencias")
    @Operation(summary = "Transferir estoque", description = "Transfere uma quantidade de estoque de um produto de origem para um produto de destino.")
    public ResponseEntity<Void> realizarTransferencia(@RequestBody @Valid TransferenciaRequestDTO dto){
        service.realizarTransferencia(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
