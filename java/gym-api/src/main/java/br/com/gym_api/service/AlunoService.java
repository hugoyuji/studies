package br.com.gym_api.service;

import br.com.gym_api.model.Aluno;
import br.com.gym_api.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    public Aluno cadastrar(Aluno aluno){
        return repository.save(aluno);
    }

    public Aluno buscarPorId(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado."));
    }

    public List<Aluno> listar(){
        return repository.findAll();
    }

    public Aluno alterar(Long id, Aluno aluno){
        Aluno alunoExistente = buscarPorId(id);

        alunoExistente.setNome(aluno.getNome());
        alunoExistente.setEmail(aluno.getEmail());
        alunoExistente.setTelefone(aluno.getTelefone());
        alunoExistente.setDataNascimento(aluno.getDataNascimento());
        alunoExistente.setDataMatricula(aluno.getDataMatricula());
        alunoExistente.setAtivo(aluno.isAtivo());

        return repository.save(alunoExistente);
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }
}
