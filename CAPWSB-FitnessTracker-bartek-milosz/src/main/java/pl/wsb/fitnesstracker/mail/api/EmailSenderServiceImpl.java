package pl.wsb.fitnesstracker.mail.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@EnableScheduling
public class EmailSenderServiceImpl implements EmailSender{

    private final JavaMailSender javaMailSender;

    @Override
    @Async
    public void send(EmailDto emailDto) {
        try {

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            simpleMailMessage.setTo(emailDto.toAddress());
            simpleMailMessage.setSubject(emailDto.subject());
            simpleMailMessage.setText(emailDto.content());

            javaMailSender.send(simpleMailMessage);
            log.info("Email sent to {}", emailDto.toAddress());

        } catch (Exception e) {
            log.error("Email: {}, Error: {}", emailDto.toAddress(), e.getMessage());
        }
    }
}
