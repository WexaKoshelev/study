package com.springstudy.study.online_movie_theater.utils;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
public class MailUtils {
    private MailUtils() {
    }


    public static SimpleMailMessage createMailMessage(final String email,
                                                      final String subject,
                                                      final String text) {
        return createMailMessage(
                Stream.of(email).toArray(String[]::new),
                subject,
                text
        );
    }

    public static SimpleMailMessage createMailMessage(final List<String> emails,
                                                      final String subject,
                                                      final String text) {
        return createMailMessage(
                emails.toArray(String[]::new),
                subject,
                text
        );


    }

    private static SimpleMailMessage createMailMessage(final String[] emails,
                                                       final String subject,
                                                       final String text) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("libraryproject@bk.ru"); //TODO: Rewrite to config
        mailMessage.setTo(emails);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        return mailMessage;
    }
}
