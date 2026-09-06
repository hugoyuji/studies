package br.com.magalu.service;

import br.com.magalu.dto.AgendamentoRequestDTO;
import br.com.magalu.dto.AgendamentoResponseDTO;
import br.com.magalu.enums.StatusAgendamento;
import br.com.magalu.model.Agendamento;
import br.com.magalu.repository.AgendamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository repository;

    public AgendamentoResponseDTO agendar(AgendamentoRequestDTO agendamentoRequestDTO){
        Agendamento agendamento = Agendamento.builder()
                .destinatario(agendamentoRequestDTO.destinatario())
                .mensagem(agendamentoRequestDTO.mensagem())
                .tipoComunicacao(agendamentoRequestDTO.tipoComunicacao())
                .dataHoraEnvio(agendamentoRequestDTO.dataHoraEnvio())
                .status(StatusAgendamento.AGENDADO)
                .criadoEm(LocalDateTime.now())
                .build();

        return toResponse (repository.save(agendamento));
    }

    public AgendamentoResponseDTO buscarPorId(Long id){
        Agendamento agendamento = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado!"));
        return toResponse(agendamento);
    }

    public void remover (Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Agendamento não encontrado.");
        }
        repository.deleteById(id);
    }

    public AgendamentoResponseDTO toResponse(Agendamento agendamento){
        return new AgendamentoResponseDTO(
                agendamento.getId(),
                agendamento.getDestinatario(),
                agendamento.getMensagem(),
                agendamento.getTipoComunicacao(),
                agendamento.getDataHoraEnvio(),
                agendamento.getStatus(),
                agendamento.getCriadoEm()
        );
    }
}
