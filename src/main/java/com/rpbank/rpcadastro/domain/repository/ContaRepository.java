package com.rpbank.rpcadastro.domain.repository;

import com.rpbank.rpcadastro.domain.entity.Conta;
import com.rpbank.rpcadastro.domain.projection.AccountDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;



public interface ContaRepository extends JpaRepository<Conta, Long>, JpaSpecificationExecutor<Conta> {

    @Query(value="select c.idConta, c.nuConta, c.flAtivo, c.idPessoa, c.nuAgencia, c.nuBanco, c.tpConta from Conta c where c.nuConta =:nuConta", nativeQuery = true)
    Conta findByNuConta(String nuConta);

    @Query(value="select \n" +
            "c.idConta,\n" +
            "c.idPessoa,\n" +
            "cp.nmPessoa,\n" +
            "c.nuAgencia,\n" +
            "c.nuConta,\n" +
            "c.nuBanco,\n" +
            "cp.cpfPessoa,\n"+
            "cp.rgPessoa,\n"+
            "c.flAtivo,\n" +
            "c.tpConta,\n" +
            "(select tc.dsConta from tipo_conta tc where tc.tpConta = c.tpConta ) as dsTipoConta\n" +
            "from rpbank.conta c\n" +
            "JOIN rpbank.cadastro_pf cp ON c.idPessoa = cp.idPessoa\n" +
            "and c.nuConta = :nuConta ", nativeQuery = true)
    AccountDetailsProjection findByDetailsNuConta(@Param("nuConta") String nuConta);


    List<Conta> findByIdContaAndNuConta(Long idConta, String nuConta);

    /*@Query("select \n" +
            "c.IdConta,\n" +
            "c.flAtivo,\n" +
            "c.tpconta,\n" +
            "(select tc.dsConta from tipo_conta tc where tc.tpConta = c.tpConta ) as dstpconta,\n" +
            "c.nuAgencia,\n" +
            "c.nuConta,\n" +
            "c.nuBanco,\n" +
            "cp.nmPessoa,\n" +
            "cp.cpfPessoa,\n" +
            "cp.rgPessoa\n" +
            "from conta c\n" +
            "JOIN cadastro_pf cp ON c.idPessoa = :idPessoa\n" +
            "and c.nuConta = :nuConta")*/

 //   @Query("select c.IdConta, c.nuConta from conta c where c.nuConta = :nuConta and c.idPessoa = :idPessoa")
 //   ResponseDetailsAccountDTO findAccountDetails(@Param("idPessoa") Long idPessoa, @Param("nuConta") Integer nuConta);

}
