package com.rpbank.rpcadastro.domain.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class BalanceHistoryDto {

    private Long idHistoricoTransacao;
    private String cdTransacao;
    private String dataTransacao;
    private BigDecimal valorTransacao;
    private BigDecimal saldoDia;
    private Long idConta;
    private String descricaoTransacao;
    private String nuContaTitular;
    private String cpfTitular;
    private String nmContaTitular;

}
