package com.rpbank.rpcadastro.service;

import com.rpbank.rpcadastro.domain.entity.Carteira;
import com.rpbank.rpcadastro.domain.repository.CarteiraRepository;
import com.rpbank.rpcadastro.domain.repository.LogCarteiraRepository;
import com.rpbank.rpcadastro.mapper.LogCarteiraMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class LogService {

    private final LogCarteiraRepository logCarteiraRepository;
    public void saveLgCarteira(Carteira currentWalletBeforeUpdate, String msg) {

        logCarteiraRepository.save(LogCarteiraMapper.toLogCarteira(currentWalletBeforeUpdate, msg));

    }
}
