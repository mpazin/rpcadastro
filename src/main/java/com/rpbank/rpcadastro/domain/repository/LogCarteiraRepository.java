package com.rpbank.rpcadastro.domain.repository;

import com.rpbank.rpcadastro.domain.entity.Carteira;
import com.rpbank.rpcadastro.domain.entity.LogCarteira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface LogCarteiraRepository extends JpaRepository<LogCarteira, Long>, JpaSpecificationExecutor<LogCarteira> {


}
