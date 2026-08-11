package br.com.gym_api.service;

import br.com.gym_api.exception.AlunoEmailJaCadastradoException;
import br.com.gym_api.exception.AlunoNaoEncontradoException;
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

        if(repository.existsByEmail(aluno.getEmail())){
            throw new AlunoEmailJaCadastradoException("E-mail já cadastrado.");
        }

        return repository.save(aluno);
    }

    public Aluno buscarPorId(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new AlunoNaoEncontradoException("Aluno não encontrado."));
    }

    public List<Aluno> listar(){
        return repository.findAll();
    }

    public Aluno alterar(Long id, Aluno aluno){
        Aluno alunoExistente = buscarPorId(id);

        if(repository.existsByEmailAndIdNot(aluno.getEmail(), id)){
            throw new AlunoEmailJaCadastradoException("E-mail já cadastrado.");
        }

        alunoExistente.setNome(aluno.getNome());
        alunoExistente.setEmail(aluno.getEmail());
        alunoExistente.setTelefone(aluno.getTelefone());
        alunoExistente.setDataNascimento(aluno.getDataNascimento());
        alunoExistente.setDataMatricula(aluno.getDataMatricula());
        alunoExistente.setAtivo(aluno.isAtivo());

        return repository.save(alunoExistente);
    }

    public void deletar(Long id){
        buscarPorId(id);
        repository.deleteById(id);
    }
}
