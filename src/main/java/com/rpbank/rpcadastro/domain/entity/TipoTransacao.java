package com.rpbank.rpcadastro.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Table(name = "tipo_transacao")
@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipoTransacao {

    @Id
    private Long tpTransacao;
    private String dsTransacao;


}
