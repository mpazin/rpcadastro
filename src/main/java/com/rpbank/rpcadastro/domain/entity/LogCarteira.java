package com.rpbank.rpcadastro.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "log_carteira")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogCarteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLogCarteira", nullable = false)
    private Long idLogCarteira;

    private LocalDateTime dtLogCadastro;
    private String lgLogCadastro;
    private String msgLogInfo;


    private Long idCarteira;

    private Long idConta;

    private String nuConta;

    private BigDecimal saldo;

    private LocalDate dtCadastro;

    private LocalDateTime dtAlteracao;
}
