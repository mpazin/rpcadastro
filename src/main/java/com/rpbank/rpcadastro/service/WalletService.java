package com.rpbank.rpcadastro.service;


import com.google.gson.Gson;
import com.rpbank.rpcadastro.domain.dto.*;
import com.rpbank.rpcadastro.domain.entity.Carteira;
import com.rpbank.rpcadastro.domain.entity.Conta;
import com.rpbank.rpcadastro.domain.entity.Transacao;
import com.rpbank.rpcadastro.domain.projection.AccountDetailsProjection;
import com.rpbank.rpcadastro.domain.repository.CarteiraRepository;
import com.rpbank.rpcadastro.domain.repository.TransacaoRepository;
import com.rpbank.rpcadastro.mapper.LogCarteiraMapper;
import com.rpbank.rpcadastro.mapper.TransacaoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@Service
public class WalletService {

    private final CarteiraRepository carteiraRepository;
    private final TransacaoRepository transacaoRepository;
    private final AccountService accountService;
    private final LogService logService;

    @Value("${codBancoDefault}")
    private Integer codBanco;

    private final String msgSuccsess = "UPDATE SUCCESS";

    Gson gson = new Gson();

    public String getBalance(String nuConta) {

        Carteira balance = carteiraRepository.findByNuConta(nuConta);

        if (balance != null) {
            return gson.toJson(ResponseWalletBalanceDTO.builder()
                    .idCarteira(balance.getIdCarteira())
                    .nuConta(balance.getNuConta())
                    .saldo(balance.getSaldo())
                    .nmPessoa(balance.getIdConta().getIdPessoa().getNmPessoa())
                    .cpfPessoa(balance.getIdConta().getIdPessoa().getCpfPessoa())
                    .dtCadastro(balance.getDtCadastro().toString())
                    .dtAlteracao(balance.getDtAlteracao().toString())
                    .build());

        }

        return null;
    }

    @Transactional(rollbackFor = {Exception.class})
    public String makeDeposit(RequestDepositWalletDTO requestDeposityWalletDTO) {

        /*deposito para correntista*/
        if (requestDeposityWalletDTO.getNuBancoDestinatario().equals(codBanco)) {

            var destinationAccountDetail = getAccountDetail(requestDeposityWalletDTO.getNuContaDestinatario());
            if (destinationAccountDetail == null) {
                return gson.toJson(ResponseWalletDepositDTO.builder().messageInfo("Conta destino inexistente").build());
            }
            requestDeposityWalletDTO.setIdConta(destinationAccountDetail.getIdConta());
            Carteira walletBalance = carteiraRepository.findByNuConta(requestDeposityWalletDTO.getNuContaDestinatario());

            BigDecimal originalBalance = walletBalance.getSaldo();
            BigDecimal newBalance = makesCredit(requestDeposityWalletDTO.getValor(), originalBalance);

            log.info("saldo: {} + deposito: {} = novo saldo: {}", walletBalance.getSaldo(), requestDeposityWalletDTO.getValor(), newBalance);

            var codE2E = geraCodE2E();
            var codTransacao = geraCodTransacao();

            /*grava logo antes do update*/
            logService.saveLgCarteira(walletBalance, msgSuccsess);

            walletBalance.setSaldo(newBalance);
            walletBalance.setDtAlteracao(LocalDateTime.now());
            carteiraRepository.save(walletBalance);

            /*Aqui teria acesso a um ENUM com os tipos de transação*/
            Long tipoTransacao = 1L;
            Transacao transacao = transacaoRepository.save(TransacaoMapper.toTbTransacaoDeposit(walletBalance, originalBalance, newBalance, codE2E, codTransacao, tipoTransacao, requestDeposityWalletDTO));
            //logRepository(transacao);

            return toResponseDepositWalletDTOJson(transacao, destinationAccountDetail);
        } else {
            /*deposito para banco terceiro*/
            /*Aqui seria logica para deposito para outros bancos*/

        }

        return null;

    }

    @Transactional(rollbackFor = {Exception.class})
    public String makeWithdrawal(RequestWithdrawalWalletDTO requestWithdrawalWalletDTO) {

        Carteira walletBalance = carteiraRepository.findByNuConta(requestWithdrawalWalletDTO.getNuConta());
        int result = walletBalance.getSaldo().compareTo(requestWithdrawalWalletDTO.getValor());
        if (result >= 0) {
            var originalBalance = walletBalance.getSaldo();

            BigDecimal newBalance = makesWithdrall(requestWithdrawalWalletDTO.getValor(), walletBalance.getSaldo());
            log.info("saldo: {} - saque: {} = novo saldo: {}", walletBalance.getSaldo(), requestWithdrawalWalletDTO.getValor(), newBalance);

            var codE2E = geraCodE2E();
            var codTransacao = geraCodTransacao();

            /*grava logo antes do update*/
            logService.saveLgCarteira(walletBalance, msgSuccsess);

            walletBalance.setSaldo(newBalance);
            walletBalance.setDtAlteracao(LocalDateTime.now());
            Carteira carteira = carteiraRepository.save(walletBalance);

            /*Aqui teria acesso a um ENUM com os tipos de transação*/
            Long tipoTransacao = 2L;
            Transacao transacao = transacaoRepository.save(TransacaoMapper.toTbTransacaoSaque(walletBalance, originalBalance, newBalance, codE2E, codTransacao, tipoTransacao, requestWithdrawalWalletDTO));
            //logRepository(transacao);

            return gson.toJson(ResponseWalletWithdrawalDTO.builder()
                    .message("Saque efetuado com sucesso")
                    .codeMessage("S001")
                    .idCarteira(walletBalance.getIdCarteira())
                    .nuConta(walletBalance.getNuConta())
                    .valorSaque(requestWithdrawalWalletDTO.getValor())
                    .saldoAtual(carteira.getSaldo())
                    .nmPessoa(walletBalance.getIdConta().getIdPessoa().getNmPessoa())
                    .cpfPessoa(walletBalance.getIdConta().getIdPessoa().getCpfPessoa())
                    .dtTransacao(transacao.getDataTransacao().toString())
                    .build());
        } else {
            return gson.toJson(ResponseWalletWithdrawalDTO.builder()
                    .message("Saldo insuficiente")
                    .codeMessage("NS001").build());
        }

    }


    @Transactional(rollbackFor = {Exception.class})
    public String makeTransfer(RequestTransferWalletDTO requestTransferWalletDTO) {

        /* verifica se o remetente tem saldo */
        Carteira walletBalanceSender = carteiraRepository.findByNuConta(requestTransferWalletDTO.getNuContaRemetente());
        int result = walletBalanceSender.getSaldo().compareTo(requestTransferWalletDTO.getValor());
        var originalBalanceSender = walletBalanceSender.getSaldo();
        if (result >= 0) {

            /* verifica se a conta de destino existe. Se sim recupera saldo atual da carteira (Transferencia Interna) */
            var destinationAccountDetail = getAccountDetail(requestTransferWalletDTO.getNuContaDestinatario());
            if (destinationAccountDetail == null) {
                return gson.toJson(ResponseWalletTransferDTO.builder().messageInfo("Conta destino inexistente").codeMessage("CI001").build());
            }
            Carteira walletBalanceReceiver = carteiraRepository.findByNuConta(requestTransferWalletDTO.getNuContaDestinatario());
            if(walletBalanceReceiver == null){
                return gson.toJson(ResponseWalletTransferDTO.builder().messageInfo("Conta destino existe, mas a carteira pode estar inativada ou não existe").codeMessage("CI002").build());
            }
            var originalBalanceReceiver = walletBalanceReceiver.getSaldo();

            /* retira o valor*/
            BigDecimal newBalanceSender = makesWithdrall(requestTransferWalletDTO.getValor(), walletBalanceSender.getSaldo());
            log.info("SENDER: saldo: {} - saque: {} = novo saldo: {}", walletBalanceReceiver.getSaldo(), requestTransferWalletDTO.getValor(), newBalanceSender);

            /*grava logo antes do update*/
            logService.saveLgCarteira(walletBalanceSender, msgSuccsess);

            walletBalanceSender.setSaldo(newBalanceSender);
            walletBalanceSender.setDtAlteracao(LocalDateTime.now());
            carteiraRepository.save(walletBalanceSender);

            /*credita o valor*/
            BigDecimal newBalanceReceiver = makesCredit(requestTransferWalletDTO.getValor(), walletBalanceReceiver.getSaldo());
            log.info("RECEIVER: saldo: {} - saque: {} = novo saldo: {}", walletBalanceReceiver.getSaldo(), requestTransferWalletDTO.getValor(), newBalanceReceiver);

            /*grava logo antes do update*/
            logService.saveLgCarteira(walletBalanceReceiver, msgSuccsess);

            walletBalanceReceiver.setSaldo(newBalanceReceiver);
            walletBalanceReceiver.setDtAlteracao(LocalDateTime.now());
            carteiraRepository.save(walletBalanceReceiver);
            //logRepository(transacao);

            var codE2E = geraCodE2E();
            var codTransacao = geraCodTransacao();

            /**Grava transacao saida SENDER*/
            Long tipoTransacao = 3L;
            Transacao transacaoSender = transacaoRepository.save(TransacaoMapper.toTbTransacaoTransfer(
                    originalBalanceSender,
                    newBalanceSender,
                    walletBalanceSender,
                    walletBalanceReceiver,
                    codE2E,
                    codTransacao,
                    tipoTransacao,
                    walletBalanceSender.getIdConta(),
                    "Transferencia - Saída",
                    requestTransferWalletDTO));
            //logRepository(transacao);

            /**Grava transacao entrada RECEIVER*/
            tipoTransacao = 6L;
            Transacao transacaoReceiver = transacaoRepository.save(TransacaoMapper.toTbTransacaoTransfer(
                    originalBalanceReceiver,
                    newBalanceReceiver,
                    walletBalanceSender,
                    walletBalanceReceiver,
                    codE2E,
                    codTransacao,
                    tipoTransacao,
                    walletBalanceReceiver.getIdConta(),
                    "Transferencia - Crédito",
                    requestTransferWalletDTO));
            //logRepository(transacao);

            return gson.toJson(ResponseWalletTransferDTO.builder()
                    .messageInfo("Tranferência efetuado com sucesso")
                    .codeMessage("T001")
                    .idCarteira(walletBalanceSender.getIdCarteira())
                    .nuConta(walletBalanceSender.getNuConta())
                    .nuContaDestino(requestTransferWalletDTO.getNuContaDestinatario())
                    .nmDestino(walletBalanceReceiver.getIdConta().getIdPessoa().getNmPessoa())
                    .cpfDestino(walletBalanceReceiver.getIdConta().getIdPessoa().getCpfPessoa())
                    .valorTranferencia(requestTransferWalletDTO.getValor())
                    .saldoAtual(walletBalanceSender.getSaldo())
                    .nmPessoa(walletBalanceSender.getIdConta().getIdPessoa().getNmPessoa())
                    .cpfPessoa(walletBalanceSender.getIdConta().getIdPessoa().getCpfPessoa())
                    .dtTransacao(transacaoSender.getDataTransacao().toString())
                    .build());

        } else {
            return gson.toJson(ResponseWalletTransferDTO.builder()
                    .messageInfo("Saldo insuficiente")
                    .codeMessage("NS001").build());
        }
    }

    public String getBalanceHistory(String nuConta, LocalDate dtInicio, LocalDate dtFim) {
        List<Transacao> balanceHistory = transacaoRepository.findBalanceHistoryByDate(nuConta, dtInicio, dtFim);
        return toResponseWalletBalanceHistoryDTOJson(balanceHistory);
    }

    private String toResponseWalletBalanceHistoryDTOJson(List<Transacao> balanceHistory) {
        ResponseWalletBalanceHistoryDTO balance = new ResponseWalletBalanceHistoryDTO();
        List<BalanceHistoryDto> listBalanceHistory = new ArrayList<>();
        if(!balanceHistory.isEmpty()){
            balanceHistory.forEach(reg -> {
                        listBalanceHistory.add(
                                BalanceHistoryDto.builder()
                                .idHistoricoTransacao(reg.getIdHistoricoTransacao())
                                        .idConta(reg.getIdConta().getIdConta())
                                        .cpfTitular(reg.getIdConta().getIdPessoa().getCpfPessoa())
                                        .nmContaTitular(reg.getIdConta().getIdPessoa().getNmPessoa())
                                        .nuContaTitular(reg.getNuContaTitular())
                                        .dataTransacao(reg.getDataTransacao().toString())
                                        .descricaoTransacao(reg.getDescricao())
                                        .valorTransacao(reg.getValorTransacao())
                                        .cdTransacao(reg.getCdTransacao())
                                        .saldoDia(reg.getSaldoPosTransacao())
                                        .build()
                        );
                    }
            );
            balance.setBalanceHistory(listBalanceHistory);
            var objectJson= gson.toJson(balance);
            return objectJson;
        }else{
            return gson.toJson(balance);
        }


    }

    private BigDecimal makesWithdrall(BigDecimal valor, BigDecimal saldo) {
        BigDecimal newBalance = saldo.subtract(valor);
        return newBalance;
    }

    private BigDecimal makesCredit(BigDecimal val, BigDecimal originalBalance) {
        List<BigDecimal> values = new LinkedList<>();
        values.add(originalBalance);
        values.add(val);
        BigDecimal newBalance = values.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        return newBalance;
    }

    private AccountDetailsProjection getAccountDetail(String nuConta) {
        AccountDetailsProjection accountDetailsProjection = accountService.getAccountDetails(nuConta);
        return accountDetailsProjection;

    }


    private String toResponseDepositWalletDTOJson(Transacao transacao, AccountDetailsProjection destinationAccountDetail) {


        return gson.toJson(ResponseWalletDepositDTO.builder()
                .nuConta(destinationAccountDetail.getNuConta())
                .idConta(destinationAccountDetail.getIdConta())
                .valor(transacao.getValorTransacao())
                .nmRemetente(transacao.getNmRemetente() != null ? transacao.getNmRemetente() : "Não preenchido")
                .cpfCnpjRemetente(transacao.getCpfCnpjRemetente() != null ? transacao.getCpfCnpjRemetente() : "Não preenchido")
                .nmDestinatario(transacao.getNmDestinatario())
                .cpfCnpjDestinatario(transacao.getCpfCnpjDestinatario())
                .saldoAnterior(transacao.getSaldoAnterior())
                .saldoAtual(transacao.getSaldoPosTransacao())
                .dataTransacao(transacao.getDataTransacao().toString())
                .tpTransacao(transacao.getTpTransacao().getTpTransacao())
                .cdTransacao(transacao.getCdTransacao())
                .cdE2E(transacao.getCdEndToEnd())
                .messageInfo("Deposito efetuado com sucesso")
                .build());
    }

    /*
     * Gera código End to End - exemplo */
    private String geraCodE2E() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyddMMHHmmss");
        LocalDateTime dateTime = LocalDateTime.now();
        var cod = dateTime.format(formatter);
        String e2e = "E2E" + codBanco + cod + codBanco;
        return e2e;
    }


    /*
     * Gera código de transacao - exemplo */
    private String geraCodTransacao() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyddMMHHmmss");
        LocalDateTime dateTime = LocalDateTime.now();
        var cod = dateTime.format(formatter);

        Random random = new Random();
        int nu = (random.nextInt(1000 - (100 - 1)) + 100);

        return "RPPAY" + codBanco + cod + "-" + nu;
    }



}
