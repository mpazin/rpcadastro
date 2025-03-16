package com.rpbank.rpcadastro.mapper;

import com.rpbank.rpcadastro.domain.entity.Carteira;
import com.rpbank.rpcadastro.domain.entity.LogCarteira;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class LogCarteiraMapper {
    public static LogCarteira toLogCarteira(Carteira currentWalletBeforeUpdate, String msg) {

       return LogCarteira.builder()
                .dtLogCadastro(LocalDateTime.now())
                .lgLogCadastro("Sistema_RP")
                .idCarteira(currentWalletBeforeUpdate.getIdCarteira())
                .idConta(currentWalletBeforeUpdate.getIdConta().getIdConta())
                .nuConta(currentWalletBeforeUpdate.getNuConta())
                .saldo(currentWalletBeforeUpdate.getSaldo())
                .dtCadastro(currentWalletBeforeUpdate.getDtCadastro())
                .dtAlteracao(currentWalletBeforeUpdate.getDtAlteracao())
                .msgLogInfo(msg)
                .build();
    }
}
