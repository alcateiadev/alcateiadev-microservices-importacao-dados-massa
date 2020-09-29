package com.br.alcateiadev.gravarvendedor.gateway.repository;

import com.br.alcateiadev.gravarvendedor.domain.Vendedor;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface VendedorRepository extends CrudRepository<Vendedor, UUID> {
    Vendedor getByCpf(String cpf);
}
