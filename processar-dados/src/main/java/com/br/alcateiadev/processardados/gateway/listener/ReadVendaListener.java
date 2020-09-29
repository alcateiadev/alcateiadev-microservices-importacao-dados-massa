package com.br.alcateiadev.processardados.gateway.listener;

import com.br.alcateiadev.processardados.domain.Venda;
import com.br.alcateiadev.processardados.gateway.json.VendaJson;
import com.br.alcateiadev.processardados.service.venda.SaveVendaService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ReadVendaListener {

    @Autowired
    private SaveVendaService saveVendaService;

    @KafkaListener(topics = "${kafka.topicgravardadoss2}", groupId = "${kafka.consumergroup}")
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
