package com.home.mail.sender.config;

import com.home.mail.sender.MySimpleMailSender;
import com.home.mail.sender.HelloWorldSenderImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Andrey on 21.01.2017.
 */

@Configuration
@PropertySource("classpath:app.properties")
public class Config {

    @Value("${smtp.host}")
    private String smtpHost;

    @Value("${smtp.port}")
    private String smtpPort;

    @Value("${mail.username}")
    private String mailUsername;

    @Value("${mail.password}")
    private String mailPassword;

    @Value("${smtp.protocol}")
    private String smtpProtocol;

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl res = new JavaMailSenderImpl();
        res.setHost(smtpHost);
        res.setPort(Integer.parseInt(smtpPort));
        res.setUsername(mailUsername);
        res.setPassword(mailPassword);
        res.setProtocol(smtpProtocol);

        Properties props = new Properties();
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.smtp.quitwait", "false");
        props.put("mail.smtp.socketFactory.fallback", "false");
        res.setJavaMailProperties(props);

        return res;
    }

    @Bean
    public MimeMessage message() {
        JavaMailSender mailSender = mailSender();
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        return mimeMessage;
    }

    @Bean
    public MySimpleMailSender helloWorldMailSender() {
        return new HelloWorldSenderImpl(message(), mailSender());
    }

}
