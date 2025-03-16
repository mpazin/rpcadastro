package com.rpbank.rpcadastro.service;


import com.google.gson.Gson;
import com.rpbank.rpcadastro.domain.dto.RequestSaveNewAccountDTO;
import com.rpbank.rpcadastro.domain.dto.ResponseDetailsAccountDTO;
import com.rpbank.rpcadastro.domain.entity.CadastroPF;
import com.rpbank.rpcadastro.domain.entity.Conta;
import com.rpbank.rpcadastro.domain.projection.AccountDetailsProjection;
import com.rpbank.rpcadastro.domain.repository.CadastroPFRepository;
import com.rpbank.rpcadastro.domain.repository.CarteiraRepository;
import com.rpbank.rpcadastro.domain.repository.ContaRepository;
import com.rpbank.rpcadastro.mapper.CadastroPFMapper;
import com.rpbank.rpcadastro.mapper.CarteiraMapper;
import com.rpbank.rpcadastro.mapper.ContaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Slf4j
@RequiredArgsConstructor
@Service
public class AccountService {

    @Value("${codBancoDefault}")
    private Integer codBanco;

    @Value("${codAgenciaDefault}")
    private Integer codAgencia;

    private final ContaRepository contaRepository;
    private final CadastroPFRepository CadastroRepository;
    private final CarteiraRepository carteiraRepository;

    public AccountDetailsProjection getAccountDetails(String nuConta) {

        AccountDetailsProjection accountDetails = contaRepository.findByDetailsNuConta(nuConta);
        return accountDetails;
    }


    @Transactional(rollbackFor = {Exception.class})
    public String createNewAccount(RequestSaveNewAccountDTO requestSaveNewAccountDTO) {

        CadastroPF cadastropf = CadastroRepository.save(CadastroPFMapper.toTbCadastroPf(requestSaveNewAccountDTO));

        Integer nuBanco = codBanco;
        Integer nuAgencia = codAgencia;
        String nuConta = geraNumeroConta();

        Conta conta = contaRepository.save(ContaMapper.toTbConta(nuBanco, nuAgencia, nuConta, requestSaveNewAccountDTO.getTpConta(), cadastropf));
        carteiraRepository.save(CarteiraMapper.toTbCarteira(conta));
        return toResponseNewAccount(cadastropf, conta);
    }

    private String toResponseNewAccount(CadastroPF cadastropf, Conta conta) {
        Gson gson = new Gson();
        return gson.toJson(ResponseDetailsAccountDTO.builder()
                .idConta(conta.getIdConta())
                .idPessoa(conta.getIdPessoa().getIdPessoa())
                .nuConta(conta.getNuConta())
                .nuAgencia(conta.getNuAgencia())
                .nuBanco(conta.getNuBanco())
                .nuCpfPessoa(cadastropf.getCpfPessoa())
                .nmPessoa(cadastropf.getNmPessoa())
                .tpConta(conta.getTpConta().getTpConta())
                .build());


    }

    private String geraNumeroConta() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyddMMHHmmss");
        LocalDateTime dateTime = LocalDateTime.now();
        return dateTime.format(formatter);

    }
}
