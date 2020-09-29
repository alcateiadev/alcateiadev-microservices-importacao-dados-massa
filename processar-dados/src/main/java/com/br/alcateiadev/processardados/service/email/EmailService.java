package com.br.alcateiadev.processardados.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${local.email.from}")
    private String from;

    @Value("${local.email.to}")
    private String to;

    public void execute(ByteArrayOutputStream pdf) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject("Email com os anexos.");
        helper.setText("Segue em anexo o pdf.");

        helper.addAttachment("relatorio.pdf",
                new ByteArrayResource(pdf.toByteArray()));

        javaMailSender.send(message);
    }
}
