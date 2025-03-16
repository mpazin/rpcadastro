package com.rpbank.rpcadastro.mapper;

import com.rpbank.rpcadastro.domain.dto.RequestDepositWalletDTO;
import com.rpbank.rpcadastro.domain.dto.RequestSaveNewAccountDTO;
import com.rpbank.rpcadastro.domain.entity.CadastroPF;
import com.rpbank.rpcadastro.domain.entity.Carteira;
import com.rpbank.rpcadastro.domain.entity.Conta;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@UtilityClass
public class CarteiraMapper {

    public Carteira toTbCarteira(Conta conta){

       return Carteira.builder()
               .idConta(conta)
               .nuConta(conta.getNuConta())
               .saldo(new BigDecimal (0.00))
               .dtAlteracao(LocalDateTime.now())
               .dtCadastro(LocalDate.now())
               .build();


    }


}
