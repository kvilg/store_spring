package com.example.demo.servis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class DefaultEmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void send(String emailTo, String subject, String message) throws MessagingException {
        try {


            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom("bit.maikers@mail.ru");
            mailMessage.setTo(emailTo);
            mailMessage.setSubject(subject);
            mailMessage.setText(message);


            mailSender.send(mailMessage);
        }catch (Exception e){
            throw new MessagingException("no find email or service don`t present");
        }
    }

}