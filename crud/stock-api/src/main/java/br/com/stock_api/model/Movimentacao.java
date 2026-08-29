package br.com.stock_api.model;

import br.com.stock_api.enums.TipoMovimentacao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "movimentacoes")
@Getter
@NoArgsConstructor
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Produto produto;

    @Column(nullable = false)
    private Integer quantidade;

    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    private TipoMovimentacao tipo;

    public Movimentacao(
            Produto produto,
            Integer quantidade,
            LocalDateTime dataHora,
            TipoMovimentacao tipo) {

        this.produto = produto;
        this.quantidade = quantidade;
        this.dataHora = dataHora;
        this.tipo = tipo;
    }
}