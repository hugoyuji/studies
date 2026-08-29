
package br.com.stock_api.controller;

import br.com.stock_api.dto.request.MovimentacaoRequestDTO;
import br.com.stock_api.dto.response.MovimentacaoResponseDTO;
import br.com.stock_api.service.MovimentacaoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {

    private final MovimentacaoService service;

    public MovimentacaoController(MovimentacaoService service) {
        this.service = service;
    }

    @PostMapping
    public MovimentacaoResponseDTO realizarMovimentacao(
            @RequestBody MovimentacaoRequestDTO dto) {

        return service.realizarMovimentacao(dto);
    }
}
