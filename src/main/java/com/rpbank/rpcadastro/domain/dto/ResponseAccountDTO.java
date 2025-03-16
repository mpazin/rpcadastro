package com.rpbank.rpcadastro.domain.dto;

import com.rpbank.rpcadastro.domain.entity.CadastroPF;
import com.rpbank.rpcadastro.domain.entity.TipoConta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseAccountDTO {
    private Long idConta;
    private CadastroPF idPessoa;
    private char flAtivo;
    private Integer nuBanco;
    private Integer nuAgencia;
    private Integer nuConta;
    private TipoConta tpConta;
}
