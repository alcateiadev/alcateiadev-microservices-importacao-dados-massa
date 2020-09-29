package com.br.alcateiadev.gravarvendedor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class GravarVendedorApplication {
    public static void main(String[] args) {
        SpringApplication.run(GravarVendedorApplication.class, args);
    }
}
