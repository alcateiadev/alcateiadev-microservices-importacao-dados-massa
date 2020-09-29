package com.br.alcateiadev.gravarvenda.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "venda")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Venda {

    @Id
    @GeneratedValue
    private UUID id;

    private String vendedorCpf;
    private Integer produtoCod;
    private String produtoNome;

    private Integer vendaCod;
    private Integer vendaQtd;
    private Double vendaValor;
    private String vendaData;

    private String lojaCidade;
    private String lojaCnpj;
    private String lojaNome;

}
