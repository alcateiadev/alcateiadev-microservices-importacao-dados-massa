package com.br.alcateiadev.processardados;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableKafka
@EnableScheduling
public class ProcessarDadosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProcessarDadosApplication.class, args);
    }

}
