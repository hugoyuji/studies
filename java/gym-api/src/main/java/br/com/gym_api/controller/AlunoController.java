package br.com.gym_api.controller;

import br.com.gym_api.dto.request.AlunoRequestDTO;
import br.com.gym_api.dto.response.AlunoResponseDTO;
import br.com.gym_api.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @PostMapping
    public AlunoResponseDTO cadastrar(@RequestBody @Valid AlunoRequestDTO alunoDTO){
        return service.cadastrar(alunoDTO);
    }

    @GetMapping("/{id}")
    public AlunoResponseDTO buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @GetMapping
    public List<AlunoResponseDTO> listar(){
        return service.listar();
    }

    @PutMapping("/{id}")
    public AlunoResponseDTO alterar(@PathVariable Long id, @RequestBody @Valid AlunoRequestDTO aluno){
        return service.alterar(id, aluno);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        service.deletar(id);
    }
}
