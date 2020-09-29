package com.br.alcateiadev.processardados.service;

import com.br.alcateiadev.processardados.service.email.EmailService;
import com.br.alcateiadev.processardados.service.pdf.GenerateReportService;
import com.br.alcateiadev.processardados.service.venda.UpdateVendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.ByteArrayOutputStream;

@Service
public class ProcessService {

    @Autowired
    private UpdateVendaService updateVendaService;

    @Autowired
    private GenerateReportService generateReportService;

    @Autowired
    private EmailService emailService;

    @Scheduled(fixedRate = 60000)
    public void execute() throws MessagingException {

        updateVendaService.execute();

        ByteArrayOutputStream pdf = generateReportService.execute();

        emailService.execute(pdf);
    }

}
