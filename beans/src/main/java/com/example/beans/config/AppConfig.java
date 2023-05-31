package com.example.beans.config;

import com.example.beans.conditions.*;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@Configuration
public class AppConfig {

    @Bean
    @ConditionalOnExpression("${logger.enabled:false} && ${logger.console.enabled:false}")
    public AppLogger consoleAppService() {
        return new ConsoleLogger();
    }

    @Bean
    @ConditionalOnExpression("${logger.enabled:false} && ${logger.journal.enabled:false}")
    public AppLogger journalAppService() {
        return new JournalLogger();
    }
    @Bean
    @ConditionalOnResource(resources = "classpath:test.json")
    public JsonParser fileParser() throws Exception {
        File file = new ClassPathResource("test.json", this.getClass().getClassLoader()).getFile();
        return new JsonParser(new String(Files.readAllBytes(Paths.get(file.toURI()))));
    }

    @Bean
    @ConditionalOnMissingBean
    public JsonParser defaultParser() throws Exception{
        return new JsonParser("{name:default}");
    }

    @Bean("email")
    @ConditionalOnBean(name = "random")
    public EmailMessage emailMessage() {
        return new EmailMessage();
    }

    @Bean("sms_class")
    @ConditionalOnClass(SmsMessage.class)
    public SmsMessage smsMessageClass() {
        return new SmsMessage();
    }
}
