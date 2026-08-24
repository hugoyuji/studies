package br.com.ticket_api.model;

import br.com.ticket_api.enums.Categoria;
import br.com.ticket_api.enums.Prioridade;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "chamados")
public class Chamado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String titulo;
    private String descricao;
    private String cliente;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    private boolean concluido;
}
