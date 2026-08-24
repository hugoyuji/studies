package br.com.ticket_api.service;

import br.com.ticket_api.dto.request.ChamadoRequestDTO;
import br.com.ticket_api.dto.response.ChamadoResponseDTO;
import br.com.ticket_api.model.Chamado;
import br.com.ticket_api.repository.ChamadoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
                .orElseThrow(() -> new RuntimeException("Id não encontrado."));

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
}
