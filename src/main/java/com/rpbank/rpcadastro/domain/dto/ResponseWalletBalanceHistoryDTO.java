package com.rpbank.rpcadastro.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseWalletBalanceHistoryDTO {

    private List<BalanceHistoryDto> balanceHistory;


}
