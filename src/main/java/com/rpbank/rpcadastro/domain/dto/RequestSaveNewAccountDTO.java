package com.rpbank.rpcadastro.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class RequestSaveNewAccountDTO {
    private String nmPessoa;
    private String cpfPessoa;
    private String rgPessoa;
    private Integer tpConta;



    @Override
    public String toString() {
        return "WalletNewAccountDTO{" +
                "nmPessoa='" + nmPessoa + '\'' +
                ", cpfPessoa='" + cpfPessoa + '\'' +
                ", rgPessoa='" + rgPessoa + '\'' +
                ", tpConta='" + tpConta + '\'' +
                '}';
    }
}
