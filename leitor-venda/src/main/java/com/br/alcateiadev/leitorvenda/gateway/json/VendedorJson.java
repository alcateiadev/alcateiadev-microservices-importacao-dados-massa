package com.br.alcateiadev.leitorvenda.gateway.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendedorJson {
    private String nome;
    private String cpf;
    private Double meta;
}