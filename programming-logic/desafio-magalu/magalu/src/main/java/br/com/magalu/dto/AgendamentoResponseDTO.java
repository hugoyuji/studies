package br.com.magalu.dto;

import br.com.magalu.enums.StatusAgendamento;
import br.com.magalu.enums.TipoComunicacao;

import java.time.LocalDateTime;

public record AgendamentoResponseDTO(
        Long id,
        String destinatario,
        String mensagem,
        TipoComunicacao tipoComunicacao,
        LocalDateTime dataHoraEnvio,
        StatusAgendamento status,
        LocalDateTime criadoEm
) {
}
