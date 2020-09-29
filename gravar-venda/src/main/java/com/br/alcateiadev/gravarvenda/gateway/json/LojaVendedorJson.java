package com.br.alcateiadev.gravarvenda.gateway.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LojaVendedorJson {
    private String cidade;
    private String cnpj;
    private String nome;
}
