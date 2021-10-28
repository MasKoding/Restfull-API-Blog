package org.spring.learn.demo.service;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.learn.demo.dao.EmailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailService  implements EmailSender{

    private final static Logger logger= LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender mailSender;

    @Override
    @Async
    public void send(String to, String email) {
        // TODO Auto-generated method stub
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,"UTF-8");

            helper.setText(email,true);
            helper.setTo(to);
            helper.setSubject("Confirm Your Email");
            helper.setFrom("8a9249edf1-16dfbb@inbox.mailtrap.io");

            mailSender.send(mimeMessage);
        } catch (Exception e) {
            //TODO: handle exception
            logger.error("failed send email :", e);
            throw new IllegalStateException("failed to send email");
        }
    }
    
}
