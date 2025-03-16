package com.rpbank.rpcadastro.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;

@Entity
@Table(name = "conta")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idConta", nullable = false)
    private Long idConta;

    @Column(name = "nuBanco", nullable = false)
    private Integer nuBanco;

    @ManyToOne
    @JoinColumn(name = "tpConta", referencedColumnName = "tpConta", columnDefinition = "int", nullable = false)
    private TipoConta tpConta;

    private Integer nuAgencia;
    private String nuConta;

    @Column(name = "flAtivo", nullable = false)
    private String flAtivo;

    @ManyToOne
    @JoinColumn(name = "idPessoa", referencedColumnName = "idPessoa", columnDefinition = "long" ,nullable = false)
    private CadastroPF idPessoa;


}
