package com.home.mail.sender.run;

import com.home.mail.sender.MySimpleMailSender;
import com.home.mail.sender.config.Config;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Andrey on 21.01.2017.
 */
public class Runner {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        MySimpleMailSender sender = ctx.getBean("helloWorldMailSender", MySimpleMailSender.class);
        sender.send();
    }
}
