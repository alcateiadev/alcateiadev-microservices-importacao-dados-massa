package com.br.alcateiadev.processardados.gateway.repository;

import com.br.alcateiadev.processardados.domain.Vendedor;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface VendedorRepository extends CrudRepository<Vendedor, UUID> {
    Vendedor getByCpf(String cpf);
}
