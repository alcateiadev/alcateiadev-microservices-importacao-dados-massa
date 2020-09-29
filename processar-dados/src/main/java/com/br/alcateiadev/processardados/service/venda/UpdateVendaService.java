package com.br.alcateiadev.processardados.service.venda;

import com.br.alcateiadev.processardados.gateway.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateVendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Transactional
    public void execute() {
        vendaRepository.updateNameVendedor();
    }
}
