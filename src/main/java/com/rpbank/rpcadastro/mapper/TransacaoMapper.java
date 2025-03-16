package com.rpbank.rpcadastro.mapper;

import com.rpbank.rpcadastro.domain.dto.RequestDepositWalletDTO;
import com.rpbank.rpcadastro.domain.dto.RequestTransferWalletDTO;
import com.rpbank.rpcadastro.domain.dto.RequestWithdrawalWalletDTO;
import com.rpbank.rpcadastro.domain.entity.Carteira;
import com.rpbank.rpcadastro.domain.entity.Conta;
import com.rpbank.rpcadastro.domain.entity.TipoTransacao;
import com.rpbank.rpcadastro.domain.entity.Transacao;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@UtilityClass
public class TransacaoMapper {

    public Transacao toTbTransacaoDeposit(Carteira walletBalance,
                                          BigDecimal originalBalance,
                                          BigDecimal newBalance,
                                          String codE2E,
                                          String codTransacao,
                                          Long tipoTransacao,
                                          RequestDepositWalletDTO requestDeposityWalletDTO) {

        return Transacao.builder()
                .cdTransacao(codTransacao)
                .dataTransacao(LocalDateTime.now())
                .cdEndToEnd(codE2E)
                .saldoPosTransacao(newBalance)
                .saldoAnterior(originalBalance)
                .nmRemetente(requestDeposityWalletDTO.getNmRemetente())
                .nuContaRemetente(requestDeposityWalletDTO.getNuContaRemetente())
                .cpfCnpjRemetente(requestDeposityWalletDTO.getCpfCnpjRemetente())
                .nmDestinatario(requestDeposityWalletDTO.getNmDestinatario())
                .nuContaDestinatario(requestDeposityWalletDTO.getNuContaDestinatario())
                .cpfCnpjDestinatario(requestDeposityWalletDTO.getCpfCnpjDestinatario())
                .chavePixDestino(null)
                .chavePixRemetente(null)
                .valorTransacao(requestDeposityWalletDTO.getValor())
                .nuAgenciaRementente(requestDeposityWalletDTO.getNuAgenciaRementente())
                .nuBancoRemetente(requestDeposityWalletDTO.getNuBancoRemetente())
                .nuAgenciaDestinatario(requestDeposityWalletDTO.getNuAgenciaDestinatario())
                .nuBancoDestinatario(requestDeposityWalletDTO.getNuBancoDestinatario())
                .idConta(Conta.builder().idConta(requestDeposityWalletDTO.getIdConta()).build())
                .descricao("Auto Deposito")
                .tpTransacao(TipoTransacao.builder().tpTransacao(tipoTransacao).build())
                .nuContaTitular(requestDeposityWalletDTO.getNuContaDestinatario())
                .build();
    }

    public static Transacao toTbTransacaoSaque(
            Carteira walletBalance,
            BigDecimal originalBalance,
            BigDecimal newBalance,
            String codE2E,
            String codTransacao,
            Long tipoTransacao,
            RequestWithdrawalWalletDTO requestWithdrawalWalletDTO) {

        return Transacao.builder()
                .cdTransacao(codTransacao)
                .dataTransacao(LocalDateTime.now())
                .cdEndToEnd(codE2E)
                .saldoPosTransacao(newBalance)
                .saldoAnterior(originalBalance)
                .valorTransacao(requestWithdrawalWalletDTO.getValor())
                .idConta(Conta.builder().idConta(walletBalance.getIdConta().getIdConta()).build())
                .nuContaTitular(requestWithdrawalWalletDTO.getNuConta())
                .descricao("Saque")
                .tpTransacao(TipoTransacao.builder().tpTransacao(tipoTransacao).build())
                .build();
    }

    public static Transacao toTbTransacaoTransfer(BigDecimal originalBalance,
                                                  BigDecimal newBalance,
                                                  Carteira walletBalanceSender,
                                                  Carteira walletBalanceReceiver,
                                                  String codE2E,
                                                  String codTransacao,
                                                  Long tipoTransacao,
                                                  Conta idConta,
                                                  String descricao,
                                                  RequestTransferWalletDTO requestTransferWalletDTO) {

        return Transacao.builder()
                .cdTransacao(codTransacao)
                .dataTransacao(LocalDateTime.now())
                .cdEndToEnd(codE2E)
                .saldoPosTransacao(newBalance)
                .saldoAnterior(originalBalance)
                .nmRemetente(walletBalanceSender.getIdConta().getIdPessoa().getNmPessoa())
                .nuContaRemetente(walletBalanceSender.getIdConta().getNuConta())
                .cpfCnpjRemetente(walletBalanceSender.getIdConta().getIdPessoa().getCpfPessoa())
                .nmDestinatario(walletBalanceReceiver.getIdConta().getIdPessoa().getNmPessoa())
                .nuContaDestinatario(walletBalanceReceiver.getIdConta().getNuConta())
                .cpfCnpjDestinatario(walletBalanceReceiver.getIdConta().getIdPessoa().getCpfPessoa())
                .chavePixDestino(null)
                .chavePixRemetente(null)
                .valorTransacao(requestTransferWalletDTO.getValor())
                .nuAgenciaRementente(walletBalanceSender.getIdConta().getNuAgencia())
                .nuBancoRemetente(walletBalanceSender.getIdConta().getNuBanco())
                .nuAgenciaDestinatario(walletBalanceReceiver.getIdConta().getNuAgencia())
                .nuBancoDestinatario(walletBalanceReceiver.getIdConta().getNuBanco())
                .idConta(idConta)
                .nuContaTitular(idConta.getNuConta())
                .descricao(descricao)
                .tpTransacao(TipoTransacao.builder().tpTransacao(tipoTransacao).build())
                .build();

    }
}
