package com.rpbank.rpcadastro.domain.repository;

import com.rpbank.rpcadastro.domain.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long>, JpaSpecificationExecutor<Transacao> {


    @Query(value="SELECT ht.* FROM rpbank.historico_transacao ht\n" +
            "WHERE\n" +
            "DATE(ht.dataTransacao) BETWEEN :dtInicio AND :dtFim\n" +
            "and ht.nuContaTitular = :nuConta order by ht.dataTransacao asc", nativeQuery = true)
    List<Transacao> findBalanceHistoryByDate(@Param("nuConta") String nuConta, @Param("dtInicio") LocalDate dtInicio,  @Param("dtFim") LocalDate dtFim);
}
