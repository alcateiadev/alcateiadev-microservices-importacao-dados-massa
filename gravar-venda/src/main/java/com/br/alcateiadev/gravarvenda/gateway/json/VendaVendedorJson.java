package com.br.alcateiadev.gravarvenda.gateway.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendaVendedorJson {
    private Integer cod;
    private Integer qtd;
    private Double valor;
    private String data;
}
