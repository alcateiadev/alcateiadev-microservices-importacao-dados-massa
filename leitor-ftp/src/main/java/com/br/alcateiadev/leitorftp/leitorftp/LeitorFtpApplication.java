package com.br.alcateiadev.leitorftp.leitorftp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LeitorFtpApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeitorFtpApplication.class, args);
	}

}
