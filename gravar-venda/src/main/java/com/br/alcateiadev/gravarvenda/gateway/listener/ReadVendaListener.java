package com.br.alcateiadev.gravarvenda.gateway.listener;

import com.br.alcateiadev.gravarvenda.domain.Venda;
import com.br.alcateiadev.gravarvenda.gateway.json.VendaJson;
import com.br.alcateiadev.gravarvenda.service.SaveVendaService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ReadVendaListener {

    @Autowired
    private SaveVendaService saveVendaService;

    @KafkaListener(topics = "${kafka.topicgravardados}", groupId = "${kafka.consumergroup}")
    public void execute(String message) throws IOException {
        VendaJson vendaJson = new Gson().fromJson(message, VendaJson.class);
        saveVendaService.execute(
                Venda
                        .builder()
                        .vendedorCpf(vendaJson.getVendedor().getCpf())
                        .produtoCod(vendaJson.getProduto().getCod())
                        .produtoNome(vendaJson.getProduto().getNome())
                        .vendaCod(vendaJson.getVenda().getCod())
                        .vendaQtd(vendaJson.getVenda().getQtd())
                        .vendaValor(vendaJson.getVenda().getValor())
                        .vendaData(vendaJson.getVenda().getData())
                        .lojaCidade(vendaJson.getLoja().getCidade())
                        .lojaCnpj(vendaJson.getLoja().getCnpj())
                        .lojaNome(vendaJson.getLoja().getNome())
                        .build()
        );
    }

}
