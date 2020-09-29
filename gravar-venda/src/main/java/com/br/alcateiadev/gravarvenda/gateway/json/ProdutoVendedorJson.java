package com.br.alcateiadev.gravarvenda.gateway.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutoVendedorJson {
    private Integer cod;
    private String nome;
}
