package kz.bloooom.administration.service;


import kz.bloooom.administration.domain.dto.user.UserResetCodeRequestDto;

public interface MailService {
    void send(String text, String subject, String email);

    void sendRegistrationMessage(String name,
                                 String email,
                                 String code,
                                 String keycloakId);

    void sendRegistrationMessageForEmployee(String name,
                                 String position,
                                 String email,
                                 String companyName,
                                 String keycloakId);

    void sendForgotPasswordCode(UserResetCodeRequestDto userResetCodeRequestDto, String resetCode);
}
