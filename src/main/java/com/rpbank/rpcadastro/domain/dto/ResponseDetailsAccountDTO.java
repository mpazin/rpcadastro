package com.rpbank.rpcadastro.domain.dto;


import lombok.Builder;

@Builder
public class ResponseDetailsAccountDTO {

    private Long idConta;
    private Long idPessoa;
    private String nuConta;
    private Integer nuAgencia;
    private Integer nuBanco;
    private String nmPessoa;
    private String nuCpfPessoa;
    private Integer tpConta;
}
