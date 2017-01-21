package com.home.mail.sender;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by Andrey on 21.01.2017.
 */
public class HelloWorldSenderImpl implements MySimpleMailSender {

    private MimeMessage message;
    private JavaMailSender mailSender;

    @Value("${mail.send.to}")
    private String sendTo;

    @Value("${mail.sent.from}")
    private String sentFrom;

    @Inject
    public HelloWorldSenderImpl(MimeMessage message, JavaMailSender mailSender) {
        this.message = message;
        this.mailSender = mailSender;
    }

    public void send() {
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message);
            messageHelper.setSubject("Hello World");
            messageHelper.setText("<html><h1>Hello</h1></html>", true);
            messageHelper.setTo(sendTo);
            messageHelper.setFrom(sentFrom);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
