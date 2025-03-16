package com.rpbank.rpcadastro.mapper;


import com.rpbank.rpcadastro.domain.entity.CadastroPF;
import com.rpbank.rpcadastro.domain.entity.Conta;
import com.rpbank.rpcadastro.domain.entity.TipoConta;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ContaMapper {

    public Conta toTbConta(Integer nuBanco, Integer nuAgencia, String nuConta, Integer tpConta, CadastroPF cadastroPF){

        Conta conta = new Conta();
        conta.setTpConta(TipoConta.builder().tpConta(tpConta).build());
        conta.setNuConta(nuConta);
        conta.setFlAtivo("S");
        conta.setNuAgencia(nuAgencia);
        conta.setNuBanco(nuBanco);
        conta.setIdPessoa(cadastroPF);
        return conta;
    }


}
