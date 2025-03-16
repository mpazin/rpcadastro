package com.rpbank.rpcadastro.domain.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class RequestWithdrawalWalletDTO {

    private String nuConta;
    private BigDecimal valor;


}
