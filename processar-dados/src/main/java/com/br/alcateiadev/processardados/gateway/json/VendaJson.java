package com.br.alcateiadev.processardados.gateway.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendaJson {
    private VendedorCodJson vendedor;
    private ProdutoVendedorJson produto;
    private VendaVendedorJson venda;
    private LojaVendedorJson loja;
}
