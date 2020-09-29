package com.br.alcateiadev.gravarvenda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class GravarVendaApplication {
    public static void main(String[] args) {
        SpringApplication.run(GravarVendaApplication.class, args);
    }
}
