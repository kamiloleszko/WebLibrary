package com.klb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Created by fmkam on 08.07.2017.
 */
@Service
public class EmailServiceImpl implements EmailService {

    
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(String fromAddress, String toAddress, String subject, String body) {
        
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toAddress);
        message.setFrom(fromAddress);
        message.setSubject("[Biblioteka] " + subject);
        message.setText(body);

        mailSender.send(message);
    }
}
