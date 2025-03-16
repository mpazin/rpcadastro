package com.rpbank.rpcadastro.domain.dto;


import lombok.Getter;

import java.math.BigDecimal;


@Getter
public class RequestTransferWalletDTO {

    private String nuContaRemetente;

    private String nuContaDestinatario;

    private BigDecimal valor;

    @Override
    public String toString() {
        return "TransferWalletRequestDTO{" +
                "nuContaRemetente='" + nuContaRemetente + '\'' +
                ", nuContaDestinatario='" + nuContaDestinatario + '\'' +
                ", valor=" + valor +
                '}';
    }
}
