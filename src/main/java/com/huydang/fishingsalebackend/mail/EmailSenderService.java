package com.huydang.fishingsalebackend.mail;
import com.huydang.fishingsalebackend.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderService {
    private final JavaMailSender mailSender;
    public void sendEmailToUser (User user, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("dangdinhhuyisme@gmail.com");
        message.setTo(user.getEmail());
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}
