package com.rpbank.rpcadastro.domain.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;


@Getter
@Setter
@ToString
public class RequestDepositWalletDTO {

    private Long idConta;
    private BigDecimal valor;
    private String nmRemetente;
    private String cpfCnpjRemetente;
    private String nuContaRemetente;
    private Integer nuAgenciaRementente;
    private Integer nuBancoRemetente;
    private String nmDestinatario;
    private String nuContaDestinatario;
    private String cpfCnpjDestinatario;
    private Integer nuAgenciaDestinatario;
    private Integer nuBancoDestinatario;

}
