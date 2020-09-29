package com.br.alcateiadev.processardados.service.venda;

import com.br.alcateiadev.processardados.domain.Venda;
import com.br.alcateiadev.processardados.gateway.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaveVendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Transactional
    public void execute(Venda venda) {
        Venda vendaDb = vendaRepository.getByVendaCod(venda.getVendaCod());

        if (vendaDb == null) {
            vendaRepository.save(venda);
        } else {
            vendaDb.setVendedorCpf(venda.getVendedorCpf());
            vendaDb.setProdutoCod(venda.getProdutoCod());
            vendaDb.setProdutoNome(venda.getProdutoNome());

            vendaDb.setVendaCod(venda.getVendaCod());
            vendaDb.setVendaQtd(venda.getVendaQtd());
            vendaDb.setVendaValor(venda.getVendaValor());
            vendaDb.setVendaData(venda.getVendaData());

            vendaDb.setLojaCidade(venda.getLojaCidade());
            vendaDb.setLojaCnpj(venda.getLojaCnpj());
            vendaDb.setLojaNome(venda.getLojaNome());

            vendaRepository.save(vendaDb);
        }
    }
}
