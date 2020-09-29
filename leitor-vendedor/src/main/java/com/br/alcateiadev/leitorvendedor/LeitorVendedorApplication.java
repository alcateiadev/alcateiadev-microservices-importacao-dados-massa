package com.br.alcateiadev.leitorvendedor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class LeitorVendedorApplication {
    public static void main(String[] args) {
        SpringApplication.run(LeitorVendedorApplication.class, args);
    }
}
