package com.br.alcateiadev.leitorvenda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class LeitorVendaApplication {
    public static void main(String[] args) {
        SpringApplication.run(LeitorVendaApplication.class, args);
    }
}
