package com.br.alcateiadev.leitorvenda.service;

import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.br.alcateiadev.leitorvenda.gateway.json.*;
import com.br.alcateiadev.leitorvenda.kafka.KafkaRequestSender;
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
    public static final String CPF_VEND = "[cpf-vend]";
    public static final String COD_PROD = "[cod-prod]";
    public static final String NOME_PROD = "[nome-prod]";
    public static final String COD_VEND = "[cod-vend]";
    public static final String QTD_VENDA = "[qtd-vend]";
    public static final String VALOR_VEND = "[valor-vend]";
    public static final String CNPJ_LOJA = "[cnpj-loja]";
    public static final String CIDADE_LOJA = "[cidade-loja]";
    public static final String NOME_LOJA = "[nome-loja]";
    public static final String DATA_VENDA = "[data-vend]";

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
            VendaJson vendaJson = VendaJson
                    .builder()
                    .venda(VendaVendedorJson
                            .builder()
                            .cod(Integer.parseInt(colsName[colsPositions.get(COD_VEND)]))
                            .data(colsName[colsPositions.get(DATA_VENDA)])
                            .valor(Double.parseDouble(colsName[colsPositions.get(VALOR_VEND)]))
                            .qtd(Integer.parseInt(colsName[colsPositions.get(QTD_VENDA)]))
                            .build()
                    ).vendedor(
                            VendedorCodJson
                                    .builder()
                                    .cpf(colsName[colsPositions.get(CPF_VEND)])
                                    .build()
                    ).loja(
                            LojaVendedorJson
                                    .builder()
                                    .cidade(colsName[colsPositions.get(CIDADE_LOJA)])
                                    .cnpj(colsName[colsPositions.get(CNPJ_LOJA)])
                                    .nome(colsName[colsPositions.get(NOME_LOJA)])
                                    .build()
                    ).produto(
                            ProdutoVendedorJson
                                    .builder()
                                    .cod(Integer.parseInt(colsName[colsPositions.get(COD_PROD)]))
                                    .nome(colsName[colsPositions.get(NOME_PROD)])
                                    .build()
                    )
                    .build();

            kafkaRequestSender.sendMessage(topicGravarDados, vendaJson);

            lineNumber++;
        }

        in.close();
    }

}
