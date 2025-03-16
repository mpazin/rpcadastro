package com.rpbank.rpcadastro.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;

@Entity
@Table(name = "cadastro_pf")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CadastroPF {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPessoa", nullable = false)
    private Long idPessoa;

    private String nmPessoa;
    private String cpfPessoa;
    private String rgPessoa;
}
