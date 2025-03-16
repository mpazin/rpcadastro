package com.rpbank.rpcadastro.domain.repository;

import com.rpbank.rpcadastro.domain.entity.TipoConta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TipoContaRepository extends JpaRepository<TipoConta, Integer> {

    List<TipoConta> findAll();
}
