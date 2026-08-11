package br.com.gym_api.controller;

import br.com.gym_api.model.Aluno;
import br.com.gym_api.service.AlunoService;
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
    public Aluno cadastrar(@RequestBody Aluno aluno){
        return service.cadastrar(aluno);
    }

    @GetMapping("/{id}")
    public Aluno buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @GetMapping
    public List<Aluno> listar(){
        return service.listar();
    }

    @PutMapping("/{id}")
    public Aluno alterar(@PathVariable Long id, @RequestBody Aluno aluno){
        return service.alterar(id, aluno);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        service.deletar(id);
    }
}
