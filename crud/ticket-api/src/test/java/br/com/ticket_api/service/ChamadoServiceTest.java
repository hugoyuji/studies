package br.com.ticket_api.service;

import br.com.ticket_api.dto.request.ChamadoRequestDTO;
import br.com.ticket_api.dto.response.ChamadoResponseDTO;
import br.com.ticket_api.enums.Categoria;
import br.com.ticket_api.enums.Prioridade;
import br.com.ticket_api.exception.ChamadoNotFoundException;
import br.com.ticket_api.model.Chamado;
import br.com.ticket_api.repository.ChamadoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ChamadoServiceTest {

    @Mock
    private ChamadoRepository repository;

    @InjectMocks
    private ChamadoService service;

    @Test
    @DisplayName("Deve cadastrar um chamado com sucesso.")
    void deveCadastrarChamadoComSucesso(){
        ChamadoRequestDTO dto = new ChamadoRequestDTO(
                "Impressora sem papel",
                "Falta papel A4",
                "João",
                Categoria.SUPORTE,
                Prioridade.MEDIA,
                false
        );

        when(repository.save(any(Chamado.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ChamadoResponseDTO response = service.cadastrar(dto);

        assertNotNull(response);
        assertEquals("Impressora sem papel", response.titulo());
        verify(repository, times(1)).save(any(Chamado.class));
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar ID inexistente.")
    void deveLancarExcecaoAoBuscarIdInexistente(){
        Long idInexistente = 99L;
        when(repository.findById(idInexistente)).thenReturn(Optional.empty());

        assertThrows(ChamadoNotFoundException.class, () -> service.buscarPorId(idInexistente));
        verify(repository, times(1)).findById(idInexistente);
    }

    @Test
    @DisplayName("Deve buscar chamado por ID com sucesso.")
    void deveBuscarIdComSucesso(){
        Long id = 1L;
        Chamado chamado = new Chamado();
        ReflectionTestUtils.setField(chamado, "id", id);
        chamado.setTitulo("Impressora sem papel");

        when(repository.findById(id)).thenReturn(Optional.of(chamado));

        ChamadoResponseDTO response = service.buscarPorId(id);

        assertNotNull(response);
        assertEquals(id, response.id());
        assertEquals("Impressora sem papel", response.titulo());
        verify(repository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve listar todos os chamados com sucesso.")
    void deveListarTodosOsChamadosComSucesso(){
        Chamado chamado = new Chamado();
        when(repository.findAll()).thenReturn(List.of(chamado));

        List<ChamadoResponseDTO> resultado = service.listar();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve alterar um chamado com sucesso quando ID existir.")
    void deveAlterarComSucesso(){
        Long id = 1L;
        Chamado chamadoExistente = new Chamado();
        ReflectionTestUtils.setField(chamadoExistente, "id", id);
        chamadoExistente.setTitulo("Título Antigo");

        ChamadoRequestDTO dtoAtualizacao = new ChamadoRequestDTO(
                "Título Novo",
                "Descrição nova",
                "Cliente novo",
                Categoria.TECNICO,
                Prioridade.ALTA,
                true
        );

        when(repository.findById(id)).thenReturn(Optional.of(chamadoExistente));
        when(repository.save(any(Chamado.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ChamadoResponseDTO response = service.alterar(dtoAtualizacao, id);

        assertNotNull(response);
        assertEquals(id, response.id());
        assertEquals("Título Novo", response.titulo());
        assertEquals("Descrição nova", response.descricao());
        assertTrue(response.concluido());

        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(any(Chamado.class));
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar alterar um chamado com ID inexistente.")
    void DeveLancarExcecaoAoAlterarIdInexistente(){
        Long idInexistente = 99L;
        ChamadoRequestDTO dto = new ChamadoRequestDTO(
                "Título",
                "Descrição",
                "Cliente",
                Categoria.SUPORTE,
                Prioridade.BAIXA,
                false
        );

        when(repository.findById(idInexistente)).thenReturn(Optional.empty());

        assertThrows(ChamadoNotFoundException.class, () -> service.alterar(dto, idInexistente));
        verify(repository, times(1)).findById(idInexistente);
        verify(repository, never()).save(any(Chamado.class));
    }

    @Test
    @DisplayName("Deve deletar chamado com sucesso quando o ID existir.")
    void deveDeletarChamadoComSucesso(){
        Long id = 1L;
        Chamado chamado = new Chamado();
        when(repository.findById(id)).thenReturn(Optional.of(chamado));
        doNothing().when(repository).deleteById(id);

        service.deletar(id);

        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).deleteById(id);
    }
}
