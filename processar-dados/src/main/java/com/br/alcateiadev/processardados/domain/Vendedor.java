package com.br.alcateiadev.processardados.domain;

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
@Table(name = "vendedor")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vendedor {

    @Id
    @GeneratedValue
    private UUID id;

    private String cpf;
    private String nome;
    private Double meta;

}
