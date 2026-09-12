package br.com.gym_api.service;

import br.com.gym_api.dto.request.AlunoRequestDTO;
import br.com.gym_api.dto.response.AlunoResponseDTO;
import br.com.gym_api.exception.AlunoEmailAlreadyExistsException;
import br.com.gym_api.exception.AlunoNotFoundException;
import br.com.gym_api.model.Aluno;
import br.com.gym_api.repository.AlunoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;

    @InjectMocks
    private AlunoService alunoService;

    @Nested
    @DisplayName("Testes do método cadastrar")
    class CadastrarAluno {

        @Test
        @DisplayName("Deve cadastrar um aluno com sucesso quando os dados forem válidos.")
        void deveCadastrarAlunoComSucesso() {
            AlunoRequestDTO requestDTO = new AlunoRequestDTO();
            requestDTO.setNome("Hugo Yuji");
            requestDTO.setEmail("hugo@email.com");
            requestDTO.setTelefone("11987654321");
            requestDTO.setDataNascimento(LocalDate.of(1998, 4, 12));
            requestDTO.setDataMatricula(LocalDate.of(2026, 9, 11));
            requestDTO.setAtivo(true);

            Aluno alunoSalvo = new Aluno(
                    1L,
                    "Hugo Yuji",
                    "hugo@email.com",
                    "11987654321",
                    LocalDate.of(1998, 4, 12),
                    LocalDate.of(2026, 9, 11),
                    true
            );

            when(alunoRepository.existsByEmail(requestDTO.getEmail())).thenReturn(false);
            when(alunoRepository.save(any(Aluno.class))).thenReturn(alunoSalvo);

            AlunoResponseDTO responseDTO = alunoService.cadastrar(requestDTO);

            assertNotNull(responseDTO);
            assertEquals(1L, responseDTO.getId());
            assertEquals(requestDTO.getNome(), responseDTO.getNome());
            assertEquals(requestDTO.getEmail(), responseDTO.getEmail());

            verify(alunoRepository, times(1)).existsByEmail(requestDTO.getEmail());
            verify(alunoRepository, times(1)).save(any(Aluno.class));
        }

        @Test
        @DisplayName("Deve lançar AlunoEmailAlreadyExistsException quando o e-mail já existir.")
        void deveLancarExcecaoQuandoEmailJaExistir() {
            AlunoRequestDTO requestDTO = new AlunoRequestDTO();
            requestDTO.setNome("Hugo Yuji");
            requestDTO.setEmail("hugo@email.com");
            requestDTO.setTelefone("11987654321");
            requestDTO.setDataNascimento(LocalDate.of(1998, 4, 12));
            requestDTO.setDataMatricula(LocalDate.of(2026, 9, 11));
            requestDTO.setAtivo(true);

            when(alunoRepository.existsByEmail(requestDTO.getEmail())).thenReturn(true);

            assertThrows(AlunoEmailAlreadyExistsException.class, () -> alunoService.cadastrar(requestDTO));

            verify(alunoRepository, times(1)).existsByEmail(requestDTO.getEmail());
            verify(alunoRepository, never()).save(any(Aluno.class));
        }
    }

    @Nested
    @DisplayName("Testes do método buscarPorId")
    class BuscarPorId {

        @Test
        @DisplayName("Deve retornar um aluno quando o ID for encontrado")
        void deveRetornarAlunoQuandoIdExistir() {
            Long id = 1L;
            Aluno aluno = new Aluno(
                    id,
                    "Hugo Yuji",
                    "hugo@email.com",
                    "11987654321",
                    LocalDate.of(1998, 4, 12),
                    LocalDate.of(2026, 9, 11),
                    true
            );

            when(alunoRepository.findById(id)).thenReturn(Optional.of(aluno));

            AlunoResponseDTO responseDTO = alunoService.buscarPorId(id);

            assertNotNull(responseDTO);
            assertEquals(id, responseDTO.getId());
            verify(alunoRepository, times(1)).findById(id);
        }

        @Test
        @DisplayName("Deve lançar AlunoNotFoundException quando o ID não for encontrado.")
        void deveLancarExcecaoQuandoIdNaoExistir() {
            Long id = 99L;
            when(alunoRepository.findById(id)).thenReturn(Optional.empty());

            assertThrows(AlunoNotFoundException.class, () -> alunoService.buscarPorId(id));
            verify(alunoRepository, times(1)).findById(id);
        }
    }
}