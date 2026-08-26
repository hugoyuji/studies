package br.com.ticket_api.controller;

import br.com.ticket_api.dto.request.ChamadoRequestDTO;
import br.com.ticket_api.dto.response.ChamadoResponseDTO;
import br.com.ticket_api.service.ChamadoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chamados")
public class ChamadoController {

    private final ChamadoService service;

    public ChamadoController(ChamadoService service) {
        this.service = service;
    }

    @PostMapping
    public ChamadoResponseDTO cadastrar(@RequestBody ChamadoRequestDTO dto){
        return service.cadastrar(dto);
    }

    @GetMapping("/{id}")
    public ChamadoResponseDTO buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @GetMapping
    public List<ChamadoResponseDTO> listar(){
        return service.listar();
    }

    @PutMapping("/{id}")
    public ChamadoResponseDTO alterar(@PathVariable Long id, @RequestBody ChamadoRequestDTO dto){
        return service.alterar(dto, id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        service.deletar(id);
    }
}
