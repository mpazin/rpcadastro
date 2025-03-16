package com.rpbank.rpcadastro.controller;

import com.rpbank.rpcadastro.domain.dto.RequestDepositWalletDTO;
import com.rpbank.rpcadastro.domain.dto.RequestTransferWalletDTO;
import com.rpbank.rpcadastro.domain.dto.RequestWithdrawalWalletDTO;
import com.rpbank.rpcadastro.service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping("/walletBalance")
    public ResponseEntity<String> getWalletBalance(@RequestParam String nuConta){
        log.info("get balance - account number receive: {}",nuConta);
        try {
            String balance = this.walletService.getBalance(nuConta);
            return ResponseEntity.status(200).body(balance);
        } catch (Exception e) {
            log.error("Não foi possivel consultar o saldo da conta {}. Erro: {}",nuConta, e.getMessage());
            return null;
        } finally {
            log.error("The 'try catch' is finished.");
        }
    }

    @PostMapping("/walletDeposit")
    public ResponseEntity<String> walletDeposit(@RequestBody RequestDepositWalletDTO requestDeposityWalletDTO){
        log.info(requestDeposityWalletDTO.toString());
        try {
            String successDeposit = this.walletService.makeDeposit(requestDeposityWalletDTO);
            return ResponseEntity.status(200).body(successDeposit);
        } catch (Exception e) {
            log.error("Não foi possivel efetuar o deposito para conta {}. Erro: {}",requestDeposityWalletDTO.getNuContaDestinatario(),e.getMessage());
            return null;
        } finally {
            log.error("The 'try catch' is finished.");
        }
    }


    @PostMapping("/walletWithdrawal")
    public ResponseEntity<String> walletWithdrawal(@RequestBody RequestWithdrawalWalletDTO requestWithdrawalWalletDTO){
        log.info(requestWithdrawalWalletDTO.toString());
        try {
            String successWithdrawal = this.walletService.makeWithdrawal(requestWithdrawalWalletDTO);
            return ResponseEntity.status(200).body(successWithdrawal);
        } catch (Exception e) {
            log.error("Não foi possivel efetuar o saque na conta {}. Erro: {}",requestWithdrawalWalletDTO.getNuConta(), e.getMessage());
            return null;
        } finally {
            log.error("The 'try catch' is finished.");
        }
    }


    @PutMapping("/walletTransfer")
    public ResponseEntity<String> walletTransfer(@RequestBody RequestTransferWalletDTO requestTransferWalletDTO){
        log.info(requestTransferWalletDTO.toString());
        try {
            String successTransfer = this.walletService.makeTransfer(requestTransferWalletDTO);
            return ResponseEntity.status(200).body(successTransfer);
        } catch (Exception e) {
            log.error("Não foi possivel efetuar o saque na conta {}. Erro: {}",requestTransferWalletDTO.getNuContaDestinatario(), e.getMessage());
            return null;
        } finally {
            log.error("The 'try catch' is finished.");
        }
    }

    @GetMapping("/balanceHistory")
    public ResponseEntity<String> getBalanceHistory(@RequestParam() String nuConta,
                                                    @RequestParam() LocalDate dtInicio,
                                                    @RequestParam() LocalDate dtFim){
        log.info("numero conta: {}",nuConta);
        log.info("data inicio: {} - data final: {}",dtInicio, dtFim);
        try {
            String balance = this.walletService.getBalanceHistory(nuConta,dtInicio, dtFim);
            return ResponseEntity.status(200).body(balance);
        } catch (Exception e) {
            log.error("Não foi possivel consultar o historico do saldo da conta {}. Erro: {}",nuConta, e.getMessage());
            return null;
        } finally {
            log.error("The 'try catch' is finished.");
        }
    }
}
