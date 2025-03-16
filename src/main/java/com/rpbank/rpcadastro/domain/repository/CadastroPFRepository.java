package com.rpbank.rpcadastro.domain.repository;

import com.rpbank.rpcadastro.domain.entity.CadastroPF;
import com.rpbank.rpcadastro.domain.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;



public interface CadastroPFRepository extends JpaRepository<CadastroPF, Long>, JpaSpecificationExecutor<CadastroPF> {

    @Override
    List<CadastroPF> findAll();
}
