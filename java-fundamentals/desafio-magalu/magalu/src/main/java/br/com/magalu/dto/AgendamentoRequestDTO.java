package br.com.magalu.dto;

import br.com.magalu.enums.TipoComunicacao;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AgendamentoRequestDTO(

        @NotBlank(message = "O destinatário é obrigatório.")
        String destinatario,

        @NotBlank(message = "A mensagem é obrigatória.")
        String mensagem,

        @NotNull(message = "O tipo de comunicação é obrigatório.")
        TipoComunicacao tipoComunicacao,

        @NotNull(message = "A data e hora de envio são obrigatórios.")
        @Future(message = "A data e hora de envio devem ser futuras.")
        LocalDateTime dataHoraEnvio
) {
}