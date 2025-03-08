package com.thmz.ecommerce.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {


    @Bean
    public JavaMailSender getMailSender() {
        return new JavaMailSenderImpl();
    }


}
