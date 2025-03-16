package com.rpbank.rpcadastro.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "historico_transacao")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idHistoricoTransacao", nullable = false)
    private Long idHistoricoTransacao;

    @Column(name = "cdTransacao", nullable = false)
    private String cdTransacao;

    @Column(name = "dataTransacao", nullable = false)
    private LocalDateTime dataTransacao;

    private String cdEndToEnd;
    private BigDecimal saldoPosTransacao;
    private BigDecimal saldoAnterior;
    private String nmRemetente;
    private String nuContaRemetente;
    private String cpfCnpjRemetente;
    private String nmDestinatario;
    private String nuContaDestinatario;
    private String cpfCnpjDestinatario;
    private String chavePixDestino;
    private String chavePixRemetente;
    private BigDecimal valorTransacao;
    private Integer nuAgenciaRementente;
    private Integer nuBancoRemetente;
    private Integer nuAgenciaDestinatario;
    private Integer nuBancoDestinatario;

    @ManyToOne
    @JoinColumn(name = "idConta", referencedColumnName = "idConta", columnDefinition = "Long")
    private Conta idConta;

    @Column(name = "nuContaTitular", nullable = false)
    private String nuContaTitular;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "tpTransacao", referencedColumnName = "tpTransacao", columnDefinition = "Long")
    private TipoTransacao tpTransacao;





}
