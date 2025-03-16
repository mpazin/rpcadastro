package com.rpbank.rpcadastro.domain.repository;

import com.rpbank.rpcadastro.domain.entity.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CarteiraRepository extends JpaRepository<Carteira, Long>, JpaSpecificationExecutor<Carteira> {

    Carteira findByNuConta(String nuConta);

    @Query(value="SELECT c.* FROM rpbank.carteira c where c.nuConta = :nuConta " +
            "ORDER BY c.idCarteira DESC LIMIT 1", nativeQuery = true)
    Carteira findCurrentWallet(@Param("nuConta") String nuConta);
}
