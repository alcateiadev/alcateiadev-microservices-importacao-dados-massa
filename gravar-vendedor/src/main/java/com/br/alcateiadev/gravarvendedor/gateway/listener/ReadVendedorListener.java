package com.br.alcateiadev.gravarvendedor.gateway.listener;

import com.br.alcateiadev.gravarvendedor.domain.Vendedor;
import com.br.alcateiadev.gravarvendedor.gateway.json.VendedorJson;
import com.br.alcateiadev.gravarvendedor.service.SaveVendedorService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ReadVendedorListener {

    @Autowired
    private SaveVendedorService saveVendedorService;

    @KafkaListener(topics = "${kafka.topicgravardados}", groupId = "${kafka.consumergroup}")
    public void execute(String message) throws IOException {
        VendedorJson vendedorJson = new Gson().fromJson(message, VendedorJson.class);
        saveVendedorService.execute(
                Vendedor
                        .builder()
                        .nome(vendedorJson.getNome())
                        .cpf(vendedorJson.getCpf())
                        .meta(vendedorJson.getMeta())
                        .build()
        );
    }

}
