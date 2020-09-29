package com.br.alcateiadev.processardados.gateway.repository;

import com.br.alcateiadev.processardados.domain.Venda;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface VendaRepository extends CrudRepository<Venda, UUID> {
    Venda getByVendaCod(Integer venda);

    @Modifying
    @Query(value = "update venda set vendedor_name = (select d.nome from vendedor d where d.cpf = vendedor_cpf) where vendedor_name is null ", nativeQuery = true)
    void updateNameVendedor();

    @Query("from Venda order by vendedorNome")
    List<Venda> findVendas();

    @Query(value = "select vendedor_cpf, vendedor_name, sum(venda_qtd) as qtd, sum(venda_valor) as valor from venda group by vendedor_name, vendedor_cpf order by vendedor_name", nativeQuery = true)
    List<Object[]> findVendasSum();
}
