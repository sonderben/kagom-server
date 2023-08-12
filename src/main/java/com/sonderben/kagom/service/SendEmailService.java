package com.sonderben.kagom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {
    @Autowired
    JavaMailSender javaMailSender;

    public void sendEmail(){
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setFrom("***REMOVED***");
        sm.setTo("***REMOVED***");
        sm.setSubject("subject tes 1 2");
        sm.setText("some text some text some text some text ");
        javaMailSender.send(sm);
        System.out.println("email send");
    }
}
