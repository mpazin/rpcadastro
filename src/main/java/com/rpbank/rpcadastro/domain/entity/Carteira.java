package com.rpbank.rpcadastro.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "carteira")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCarteira", nullable = false)
    private Long idCarteira;

    @ManyToOne
    @JoinColumn(name = "idConta", referencedColumnName = "idConta", columnDefinition = "Long", nullable = false)
    private Conta idConta;

    @Column(name = "nuConta", nullable = false)
    private String nuConta;

    @Column(name = "saldo", nullable = false)
    private BigDecimal saldo;

    @Column(name = "dtCadastro", nullable = false)
    private LocalDate dtCadastro;

    @Column(name = "dtAlteracao")
    private LocalDateTime dtAlteracao;
}
