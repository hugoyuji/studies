package br.com.ticket_api.controller;

import br.com.ticket_api.dto.request.ChamadoRequestDTO;
import br.com.ticket_api.dto.response.ChamadoResponseDTO;
import br.com.ticket_api.service.ChamadoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ChamadoResponseDTO> cadastrar(@Valid @RequestBody ChamadoRequestDTO dto){
        ChamadoResponseDTO response = service.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChamadoResponseDTO> buscarPorId(@PathVariable Long id){
        ChamadoResponseDTO response = service.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ChamadoResponseDTO>> listar(){
        List<ChamadoResponseDTO> list =  service.listar();
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChamadoResponseDTO> alterar(@PathVariable Long id,@Valid @RequestBody ChamadoRequestDTO dto){
        ChamadoResponseDTO response = service.alterar(dto, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
