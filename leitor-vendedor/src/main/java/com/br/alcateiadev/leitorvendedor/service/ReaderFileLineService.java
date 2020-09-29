package com.br.alcateiadev.leitorvendedor.service;

import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.br.alcateiadev.leitorvendedor.gateway.json.VendedorJson;
import com.br.alcateiadev.leitorvendedor.kafka.KafkaRequestSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReaderFileLineService {

    public static final String DIVIDER = "#";
    public static final String NOME_VEND = "[nome-vend]";
    public static final String CPF_VEND = "[cpf-vend]";
    public static final String META_VEND = "[meta-vend]";
    public static final String COMMA = ".";
    public static final String EMPTY = "";
    public static final String DOT = ",";

    @Autowired
    private KafkaRequestSender kafkaRequestSender;

    @Value("${kafka.topicgravardados}")
    private String topicGravarDados;

    public void execute(S3ObjectInputStream s3ObjectInputStream) throws IOException {

        Map<String, Integer> colsPositions = new HashMap<>();

        BufferedReader in = new BufferedReader(new InputStreamReader(s3ObjectInputStream));
        String line;
        int lineNumber = 0;
        while ((line = in.readLine()) != null) {

            if (lineNumber == 0) {
                lineNumber++;
                continue;
            }

            if (lineNumber == 1) {
                String[] colsName = line.split(DIVIDER);
                for (int loop = 0; loop < colsName.length; loop++) {
                    colsPositions.put(colsName[loop], loop);
                }
                lineNumber++;
                continue;
            }

            String[] colsName = line.split(DIVIDER);
            VendedorJson vendedorJson = VendedorJson
                    .builder()
                    .nome(colsName[colsPositions.get(NOME_VEND)])
                    .cpf(colsName[colsPositions.get(CPF_VEND)].replace(COMMA, EMPTY))
                    .meta(Double.parseDouble(colsName[colsPositions.get(META_VEND)].replace(DOT, EMPTY)))
                    .build();

            kafkaRequestSender.sendMessage(topicGravarDados, vendedorJson);

            lineNumber++;
        }

        in.close();
    }

}
