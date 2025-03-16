package com.rpbank.rpcadastro.domain.projection;

import com.rpbank.rpcadastro.domain.entity.CadastroPF;
import com.rpbank.rpcadastro.domain.entity.TipoConta;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccountDetailsProjection {

    private Long idConta;
    private Long idPessoa;

    private String nmPessoa;
    private Integer nuAgencia;
    private String nuConta;
    private Integer nuBanco;
    private String cpfPessoa;
    private String rgPessoa;
    private char flAtivo;
    private Integer tpConta;
    private String dsTipoConta;

    public AccountDetailsProjection(Long idConta, Long idPessoa, String nmPessoa, Integer nuAgencia, String nuConta, Integer nuBanco, String cpfPessoa, String rgPessoa, char flAtivo, Integer tpConta, String dsTipoConta) {
        this.idConta = idConta;
        this.idPessoa = idPessoa;
        this.nmPessoa = nmPessoa;
        this.nuAgencia = nuAgencia;
        this.nuConta = nuConta;
        this.nuBanco = nuBanco;
        this.cpfPessoa = cpfPessoa;
        this.rgPessoa = rgPessoa;
        this.flAtivo = flAtivo;
        this.tpConta = tpConta;
        this.dsTipoConta = dsTipoConta;
    }



}
