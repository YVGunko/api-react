package com.yg.apireact;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.yg.apireact.model.outDoorOrder.Mail;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private SpringTemplateEngine templateEngine;
    public void sendEmail(Mail mail, String templateName) throws MessagingException, IOException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        
        //helper.addAttachment("logo.png", new ClassPathResource("logo_stpls_48.PNG"));
        Context context = new Context();
        context.setVariables(mail.getProps());
    
        String html = templateEngine.process(templateName, context);
        helper.setTo(mail.getMailTo());
        helper.setText(html, true);
        helper.setSubject(mail.getSubject());
        helper.setFrom(mail.getFrom());
        if (mail.getBcc() != null) helper.setBcc(mail.getBcc());

        //Sending mail in thread because it block main thread
        new Thread(() -> emailSender.send(message)).start();
    }
}
