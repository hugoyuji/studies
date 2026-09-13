package br.com.gym_api.controller;

import br.com.gym_api.dto.request.AlunoRequestDTO;
import br.com.gym_api.dto.response.AlunoResponseDTO;
import br.com.gym_api.service.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
@Tag(name = "Alunos", description = "Endpoints para gerenciamento de alunos")
public class AlunoController {

    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Cadastra um novo aluno")
    public ResponseEntity<AlunoResponseDTO> cadastrar(@Valid @RequestBody AlunoRequestDTO dto) {
        AlunoResponseDTO aluno = service.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um aluno pelo ID")
    public ResponseEntity<AlunoResponseDTO> buscarPorId(@PathVariable Long id) {
        AlunoResponseDTO aluno = service.buscarPorId(id);
        return ResponseEntity.ok(aluno);
    }

    @GetMapping
    @Operation(summary = "Lista todos os alunos")
    public ResponseEntity<List<AlunoResponseDTO>> listar() {
        List<AlunoResponseDTO> alunos = service.listar();
        return ResponseEntity.ok(alunos);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza os dados de um aluno")
    public ResponseEntity<AlunoResponseDTO> alterar(@PathVariable Long id, @Valid @RequestBody AlunoRequestDTO dto) {
        AlunoResponseDTO aluno = service.alterar(id, dto);
        return ResponseEntity.ok(aluno);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um aluno pelo ID")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}