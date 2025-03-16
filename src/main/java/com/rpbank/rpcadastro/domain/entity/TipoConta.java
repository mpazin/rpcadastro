package com.rpbank.rpcadastro.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Table(name = "tipo_conta")
@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipoConta {

    @Id
    private Integer tpConta;
    private String dsConta;


}
