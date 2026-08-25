package br.com.magalu.controller;

import br.com.magalu.dto.AgendamentoRequestDTO;
import br.com.magalu.dto.AgendamentoResponseDTO;
import br.com.magalu.service.AgendamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/agendamentos")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService service;

    @PostMapping
    public ResponseEntity<AgendamentoResponseDTO> agendar(@Valid @RequestBody AgendamentoRequestDTO agendamentoRequestDTO) {
        AgendamentoResponseDTO agendamentoResponseDTO = service.agendar(agendamentoRequestDTO);
        return ResponseEntity.ok(agendamentoResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoResponseDTO> buscarPorId(@PathVariable Long id) {
        AgendamentoResponseDTO agendamentoResponseDTO = service.buscarPorId(id);
        return ResponseEntity.ok(agendamentoResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

}