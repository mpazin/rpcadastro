package com.rpbank.rpcadastro.domain.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;


@Getter
@Setter
@Builder
@ToString
public class ResponseWalletTransferDTO {

    private Long idCarteira;
    private String nuConta;
    private BigDecimal valorTranferencia;
    private BigDecimal saldoAtual;
    private String nmPessoa;
    private String cpfPessoa;
    private String nuContaDestino;
    private String nmDestino;
    private String cpfDestino;
    private String dtTransacao;
    private String messageInfo;
    private String codeMessage;

}
