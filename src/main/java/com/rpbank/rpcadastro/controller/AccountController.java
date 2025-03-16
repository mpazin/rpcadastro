package com.rpbank.rpcadastro.controller;

import com.rpbank.rpcadastro.domain.dto.RequestSaveNewAccountDTO;
import com.rpbank.rpcadastro.domain.projection.AccountDetailsProjection;
import com.rpbank.rpcadastro.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class AccountController {


    @Autowired
    private AccountService accountService;

    @PostMapping("/newAccount")
    public ResponseEntity<String> saveNewAccount(@RequestBody RequestSaveNewAccountDTO requestSaveNewAccountDTO){
        log.info(requestSaveNewAccountDTO.toString());
        try {
            String account = this.accountService.createNewAccount(requestSaveNewAccountDTO);
            return ResponseEntity.status(200).body(account);
        } catch (Exception e) {
            log.error("Não foi possivel criar a conta. Erro: {}",e.getMessage());
            return null;
        } finally {
            log.error("The 'try catch' is finished.");
        }

    }




    @GetMapping("/getAccount")
    public ResponseEntity<AccountDetailsProjection> getAccount(
            @RequestParam(required = true) String nuConta) {

        log.info("testando:  nuConta:{}", nuConta);
        var accountDetails = this.accountService.getAccountDetails(nuConta);

        if(accountDetails != null){
            return ResponseEntity.ok(accountDetails);
        }else{
            return ResponseEntity
                    .status(204)
                    .body(null);
        }

    }
}
