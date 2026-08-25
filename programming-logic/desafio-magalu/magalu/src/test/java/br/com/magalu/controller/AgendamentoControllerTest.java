    package br.com.magalu.controller;

    import br.com.magalu.dto.AgendamentoRequestDTO;
    import br.com.magalu.dto.AgendamentoResponseDTO;
    import br.com.magalu.enums.StatusAgendamento;
    import br.com.magalu.enums.TipoComunicacao;
    import br.com.magalu.service.AgendamentoService;
    import org.junit.jupiter.api.Test;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
    import org.springframework.http.MediaType;
    import org.springframework.test.context.bean.override.mockito.MockitoBean;
    import org.springframework.test.web.servlet.MockMvc;
    import tools.jackson.databind.ObjectMapper;

    import java.time.LocalDateTime;


    import static org.mockito.ArgumentMatchers.any;
    import static org.mockito.Mockito.doNothing;
    import static org.mockito.Mockito.when;
    import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
    import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
    import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

    @WebMvcTest(AgendamentoController.class)
    public class AgendamentoControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @MockitoBean
        private AgendamentoService service;

        @Test
        void deveAgendarComunicacao() throws Exception {

            AgendamentoRequestDTO agendamentoRequestDTO = new AgendamentoRequestDTO(
                    "cliente@gmail.com",
                    "Mensagem de teste.",
                    TipoComunicacao.EMAIL,
                    LocalDateTime.now().plusMinutes(1)
            );

            AgendamentoResponseDTO agendamentoResponseDTO = new AgendamentoResponseDTO(
                    1L,
                    agendamentoRequestDTO.destinatario(),
                    agendamentoRequestDTO.mensagem(),
                    agendamentoRequestDTO.tipoComunicacao(),
                    agendamentoRequestDTO.dataHoraEnvio(),
                    StatusAgendamento.AGENDADO,
                    LocalDateTime.now()
            );

            when(service.agendar(any())).thenReturn(agendamentoResponseDTO);

            mockMvc.perform(post("/api/agendamentos")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(agendamentoRequestDTO)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.destinatario").value("cliente@gmail.com"));
        }

        @Test
        void deveBuscarAgendamentoPorId() throws Exception {
            Long id = 1L;

            AgendamentoResponseDTO agendamentoResponseDTO = new AgendamentoResponseDTO(
                    1L,
                    "cliente@gmail.com",
                    "Mensagem de teste.",
                    TipoComunicacao.SMS,
                    LocalDateTime.now().plusMinutes(2),
                    StatusAgendamento.AGENDADO,
                    LocalDateTime.now()
            );

            when(service.buscarPorId(id)).thenReturn(agendamentoResponseDTO);

            mockMvc.perform(get("/api/agendamentos/{id}", id))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(id))
                    .andExpect(jsonPath("$.tipoComunicacao").value("SMS"));
        }

        @Test
        void deveRemoverAgendamento() throws Exception {
            Long id = 1L;

            doNothing().when(service).remover(id);

            mockMvc.perform(delete("/api/agendamentos/{id}", id))
                    .andExpect(status().isNoContent());
        }
    }