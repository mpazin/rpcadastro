package com.rpbank.rpcadastro.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class ResponseWalletBalanceDTO {

    private Long idCarteira;
    private String nuConta;
    private BigDecimal saldo;
    private String nmPessoa;
    private String cpfPessoa;
    private String dtCadastro;
    private String dtAlteracao;

}
