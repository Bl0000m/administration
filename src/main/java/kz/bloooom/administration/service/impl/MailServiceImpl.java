package kz.bloooom.administration.service.impl;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.domain.dto.user.UserResetCodeRequestDto;
import kz.bloooom.administration.exception.BloomAdministrationException;
import kz.bloooom.administration.service.I18nService;
import kz.bloooom.administration.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Slf4j
@Component
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    @Value("${bloom.stage}")
    private String stage;

    private static final String MAIL_SUBJECT = "Добро пожаловать в Bloooom.";
    private static final String FORGOT_PASS_SUBJECT = "Восстановление пароля учетной записи. %s";
    public static final String REGISTRATION_MESSAGE = "Уважаемый/ая %s," +
            "</br> Добро пожаловать в Bloooom!" +
            "</br> Ваш код подтверждения %s";

    public static final String HELLO_MESSAGE = "Уважаемый/ая %s," +
            "</br>Вы были добавлены в систему \"Bloom\" как <b>'%s'</b> компании <b>%s</b>. Для завершения регистрации и получения доступа к вашему профилю, пожалуйста, установите пароль." +
            "</br>Для этого перейдите по следующей ссылке:" +
            "</br> <a href=%s>Установить пароль</a>";

    private final JavaMailSender mailSender;
    private final I18nService i18nService;
    @Value("${spring.mail.username}")
    private String sender;

    @Value("${spring.mail.sign-in-link}")
    private String signInLink;

    /**
     * {@inheritDoc}
     */
    @Override
    public void send(String text, String subject, String email) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            message.setFrom(new InternetAddress(sender));
            message.setRecipients(MimeMessage.RecipientType.TO, email);
            message.setSubject(subject);
            message.setContent(text, "text/html; charset=utf-8");
            mailSender.send(message);
            log.info("Send message: {} to email: {}", text, email);
        } catch (Exception e) {
            log.error("Exception: send: text={}, subject={}, email={}", text, subject, email, e);
            throw new BloomAdministrationException(
                    HttpStatus.BAD_REQUEST,
                    ErrorCodeConstant.MAILING_ERROR,
                    "messages.exception.mailing-error"
            );
        }
    }

    @Override
    public void sendRegistrationMessage(String name,
                                        String email,
                                        String code,
                                        String keycloakId) {
        String[] args = {name, code};
        String message = String.format(REGISTRATION_MESSAGE, args);
        send(message, String.format(MAIL_SUBJECT), email);
    }

    @Override
    public void sendRegistrationMessageForEmployee(String name,
                                                   String position,
                                                   String email,
                                                   String companyName,
                                                   String keycloakId) {
        String responseLink = signInLink + "=" + keycloakId;
        String[] args = {name, position, companyName, responseLink};
        String message = String.format(HELLO_MESSAGE, args);
        send(message, String.format(MAIL_SUBJECT), email);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendForgotPasswordCode(UserResetCodeRequestDto userResetCodeRequestDto, String resetCode) {
        String message = i18nService.getMessage("messages.forgot-password", resetCode);
        send(message, String.format(FORGOT_PASS_SUBJECT, stage), userResetCodeRequestDto.getEmail());
    }
}
