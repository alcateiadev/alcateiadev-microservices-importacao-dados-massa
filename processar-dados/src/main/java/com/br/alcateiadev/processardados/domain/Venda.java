package com.br.alcateiadev.processardados.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @Column(name = "vendedor_name", nullable = true, length = 500)
    private String vendedorNome;

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
