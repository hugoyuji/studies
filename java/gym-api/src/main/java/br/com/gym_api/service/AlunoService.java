package br.com.gym_api.service;

import br.com.gym_api.dto.request.AlunoRequestDTO;
import br.com.gym_api.dto.response.AlunoResponseDTO;
import br.com.gym_api.exception.AlunoEmailJaCadastradoException;
import br.com.gym_api.exception.AlunoNaoEncontradoException;
import br.com.gym_api.model.Aluno;
import br.com.gym_api.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    public AlunoResponseDTO cadastrar(AlunoRequestDTO alunoRequest){

        if(repository.existsByEmail(alunoRequest.getEmail())){
            throw new AlunoEmailJaCadastradoException("E-mail já cadastrado.");
        }

        Aluno alunoEntidade = new Aluno();

        alunoEntidade.setNome(alunoRequest.getNome());
        alunoEntidade.setEmail(alunoRequest.getEmail());
        alunoEntidade.setTelefone(alunoRequest.getTelefone());
        alunoEntidade.setDataNascimento(alunoRequest.getDataNascimento());
        alunoEntidade.setDataMatricula(alunoRequest.getDataMatricula());
        alunoEntidade.setAtivo(alunoRequest.getAtivo());

        repository.save(alunoEntidade);

        AlunoResponseDTO alunoResponse = new AlunoResponseDTO();

        alunoResponse.setId(alunoEntidade.getId());
        alunoResponse.setNome(alunoEntidade.getNome());
        alunoResponse.setEmail(alunoEntidade.getEmail());
        alunoResponse.setTelefone(alunoEntidade.getTelefone());
        alunoResponse.setDataNascimento(alunoEntidade.getDataNascimento());
        alunoResponse.setDataMatricula(alunoEntidade.getDataMatricula());
        alunoResponse.setAtivo(alunoEntidade.isAtivo());

        return alunoResponse;
    }

    public AlunoResponseDTO buscarPorId(Long id){

        Aluno alunoEntidade = repository.findById(id)
                .orElseThrow(() -> new AlunoNaoEncontradoException("Aluno não encontrado."));

        AlunoResponseDTO alunoResponse = new AlunoResponseDTO();

        alunoResponse.setId(alunoEntidade.getId());
        alunoResponse.setNome(alunoEntidade.getNome());
        alunoResponse.setEmail(alunoEntidade.getEmail());
        alunoResponse.setTelefone(alunoEntidade.getTelefone());
        alunoResponse.setDataNascimento(alunoEntidade.getDataNascimento());
        alunoResponse.setDataMatricula(alunoEntidade.getDataMatricula());
        alunoResponse.setAtivo(alunoEntidade.isAtivo());

        return alunoResponse;
    }

    public List<AlunoResponseDTO> listar(){

        List<Aluno> alunosEntidade = repository.findAll();

        List<AlunoResponseDTO> alunosResponse = new ArrayList<>();

        for(Aluno aluno : alunosEntidade){

            AlunoResponseDTO alunoResponse = new AlunoResponseDTO();

            alunoResponse.setId(aluno.getId());
            alunoResponse.setNome(aluno.getNome());
            alunoResponse.setEmail(aluno.getEmail());
            alunoResponse.setTelefone(aluno.getTelefone());
            alunoResponse.setDataNascimento(aluno.getDataNascimento());
            alunoResponse.setDataMatricula(aluno.getDataMatricula());
            alunoResponse.setAtivo(aluno.isAtivo());

            alunosResponse.add(alunoResponse);
        }

        return alunosResponse;
    }

    public AlunoResponseDTO alterar(Long id, AlunoRequestDTO alunoRequest){

        Aluno alunoExistente = repository.findById(id)
                .orElseThrow(() -> new AlunoNaoEncontradoException("Aluno não encontrado."));

        if(repository.existsByEmailAndIdNot(alunoRequest.getEmail(), id)){
            throw new AlunoEmailJaCadastradoException("E-mail já cadastrado.");
        }

        alunoExistente.setNome(alunoRequest.getNome());
        alunoExistente.setEmail(alunoRequest.getEmail());
        alunoExistente.setTelefone(alunoRequest.getTelefone());
        alunoExistente.setDataNascimento(alunoRequest.getDataNascimento());
        alunoExistente.setDataMatricula(alunoRequest.getDataMatricula());
        alunoExistente.setAtivo(alunoRequest.getAtivo());

        repository.save(alunoExistente);

        AlunoResponseDTO alunoResponse = new AlunoResponseDTO();

        alunoResponse.setId(alunoExistente.getId());
        alunoResponse.setNome(alunoExistente.getNome());
        alunoResponse.setEmail(alunoExistente.getEmail());
        alunoResponse.setTelefone(alunoExistente.getTelefone());
        alunoResponse.setDataNascimento(alunoExistente.getDataNascimento());
        alunoResponse.setDataMatricula(alunoExistente.getDataMatricula());
        alunoResponse.setAtivo(alunoExistente.isAtivo());

        return alunoResponse;
    }

    public void deletar(Long id){
        buscarPorId(id);
        repository.deleteById(id);
    }
}
