
package br.com.stock_api.controller;

import br.com.stock_api.dto.request.MovimentacaoRequestDTO;
import br.com.stock_api.dto.response.MovimentacaoResponseDTO;
import br.com.stock_api.service.MovimentacaoService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {

    private final MovimentacaoService service;

    public MovimentacaoController(MovimentacaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MovimentacaoResponseDTO> realizarMovimentacao(@RequestBody MovimentacaoRequestDTO dto){
        MovimentacaoResponseDTO response = service.realizarMovimentacao(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
