package com.br.alcateiadev.gravarvenda.gateway.repository;

import com.br.alcateiadev.gravarvenda.domain.Venda;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface VendaRepository extends CrudRepository<Venda, UUID> {
    Venda getByVendaCod(Integer venda);
}
