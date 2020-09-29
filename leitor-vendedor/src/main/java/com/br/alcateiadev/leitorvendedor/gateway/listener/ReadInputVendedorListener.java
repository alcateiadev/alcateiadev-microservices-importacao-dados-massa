package com.br.alcateiadev.leitorvendedor.gateway.listener;

import com.br.alcateiadev.leitorvendedor.gateway.json.FileUUIDJson;
import com.br.alcateiadev.leitorvendedor.service.ReaderFileService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ReadInputVendedorListener {

    @Autowired
    private ReaderFileService readerFileService;

    @KafkaListener(topics = "${kafka.topictopics1}", groupId = "${kafka.consumergroup}")
    public void execute(String message) throws IOException {
        FileUUIDJson fileUUIDJson = new Gson().fromJson(message, FileUUIDJson.class);
        readerFileService.execute(fileUUIDJson);
    }

}
