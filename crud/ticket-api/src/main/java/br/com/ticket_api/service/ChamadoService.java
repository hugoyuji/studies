package br.com.ticket_api.service;

import br.com.ticket_api.dto.request.ChamadoRequestDTO;
import br.com.ticket_api.dto.response.ChamadoResponseDTO;
import br.com.ticket_api.exception.ChamadoNotFoundException;
import br.com.ticket_api.model.Chamado;
import br.com.ticket_api.repository.ChamadoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChamadoService {

    private final ChamadoRepository repository;

    public ChamadoService(ChamadoRepository repository) {
        this.repository = repository;
    }

    public ChamadoResponseDTO cadastrar(ChamadoRequestDTO dto){

        Chamado chamado = new Chamado();

        chamado.setTitulo(dto.titulo());
        chamado.setDescricao(dto.descricao());
        chamado.setCliente(dto.cliente());
        chamado.setCategoria(dto.categoria());
        chamado.setPrioridade(dto.prioridade());

        chamado.setDataHora(LocalDateTime.now());
        chamado.setConcluido(false);

        repository.save(chamado);

        return new ChamadoResponseDTO(
                chamado.getId(),
                chamado.getTitulo(),
                chamado.getDescricao(),
                chamado.getCliente(),
                chamado.getCategoria(),
                chamado.getPrioridade(),
                chamado.getDataHora(),
                chamado.isConcluido()
        );
    }

    public ChamadoResponseDTO buscarPorId(Long id){
        Chamado chamado = repository.findById(id)
                .orElseThrow(() -> new ChamadoNotFoundException(id));

        return new ChamadoResponseDTO(
                chamado.getId(),
                chamado.getTitulo(),
                chamado.getDescricao(),
                chamado.getCliente(),
                chamado.getCategoria(),
                chamado.getPrioridade(),
                chamado.getDataHora(),
                chamado.isConcluido()
        );
    }

    public List<ChamadoResponseDTO> listar(){
        List<Chamado> chamados = repository.findAll();

        List<ChamadoResponseDTO> response = new ArrayList<>();

        for (Chamado chamado: chamados){

            response.add(new ChamadoResponseDTO(
                    chamado.getId(),
                    chamado.getTitulo(),
                    chamado.getDescricao(),
                    chamado.getCliente(),
                    chamado.getCategoria(),
                    chamado.getPrioridade(),
                    chamado.getDataHora(),
                    chamado.isConcluido()
            ));
        }

        return response;
    }

    public ChamadoResponseDTO alterar(ChamadoRequestDTO dto, Long id){

        Chamado chamado = repository.findById(id)
                .orElseThrow(() -> new ChamadoNotFoundException(id));

        chamado.setTitulo(dto.titulo());
        chamado.setDescricao(dto.descricao());
        chamado.setCliente(dto.cliente());
        chamado.setCategoria(dto.categoria());
        chamado.setPrioridade(dto.prioridade());
        chamado.setConcluido(dto.concluido());

        repository.save(chamado);

        return new ChamadoResponseDTO(
                chamado.getId(),
                chamado.getTitulo(),
                chamado.getDescricao(),
                chamado.getCliente(),
                chamado.getCategoria(),
                chamado.getPrioridade(),
                chamado.getDataHora(),
                chamado.isConcluido()
        );
    }

    public void deletar(Long id){

        repository.findById(id)
                .orElseThrow(() -> new ChamadoNotFoundException(id));

        repository.deleteById(id);
    }
}
