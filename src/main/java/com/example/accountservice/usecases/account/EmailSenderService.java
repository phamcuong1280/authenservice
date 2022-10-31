package com.example.accountservice.usecases.account;

import com.example.accountservice.infrastructure.models.Account;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
@AllArgsConstructor
public class EmailSenderService {
    private final AccountUseCase accountUseCase;
    private final JavaMailSender mailSender;

    @Async
    public void sendEmail(Account account) throws MessagingException, UnsupportedEncodingException {
        account.setValidCode(RandomStringUtils.random(6, false, true));
        accountUseCase.update(account);
        MimeMessage mailMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage);

        messageHelper.setFrom("project.service.1.1.0@gmail.com", "account-service.app");
        messageHelper.setTo(account.getEmail());

        String subject = "[Account-Service]: Bro quên pass hả ?";
        messageHelper.setSubject(subject);
        String mailContents = "<p>Dear [" + account.getName() + "]</p>";
        mailContents += "<p>Code: " + account.getValidCode() + "<br></p>";
        mailContents += "<p>Sincerely.<br>" + "Application Admin <br></p>";

        mailContents += "<p>Note: This is an auto-generated email, please do not reply.</p>";
        messageHelper.setText(mailContents, true);
        mailSender.send(mailMessage);
    }
}
