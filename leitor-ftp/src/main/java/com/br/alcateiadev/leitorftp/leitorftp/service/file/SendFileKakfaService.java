package com.br.alcateiadev.leitorftp.leitorftp.service.file;

import com.br.alcateiadev.leitorftp.leitorftp.gateway.json.FileUUIDJson;
import com.br.alcateiadev.leitorftp.leitorftp.kafka.KafkaRequestSender;
import com.br.alcateiadev.leitorftp.leitorftp.service.ftp.FileTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.Scanner;

@Service
public class SendFileKakfaService {

    public static final String SISTEMA_1 = "[SISTEMA1]";
    public static final String SISTEMA_2 = "[SISTEMA2]";

    @Value("${kafka.topictopics1}")
    private String topictopics1;

    @Value("${kafka.topictopics2}")
    private String topictopics2;

    @Autowired
    private KafkaRequestSender kafkaRequestSender;

    public void execute(FileTransfer fileTransfer) throws FileNotFoundException {

        // le a primeira linha do arquivo
        Scanner in = new Scanner(fileTransfer.getPathLocal());
        String firstLine = null;
        while (in.hasNextLine()) {
            firstLine = in.nextLine();
            break;
        }
        in.close();

        String topic = null;
        if (SISTEMA_1.equals(firstLine.replaceAll("#", ""))) {
            topic = topictopics1;
        } else if (SISTEMA_2.equals(firstLine.replaceAll("#", ""))) {
            topic = topictopics2;
        }

        kafkaRequestSender.sendMessage(topic,
                FileUUIDJson
                .builder()
                .uuid(fileTransfer.getUuid())
                .build()
        );
    }
}
