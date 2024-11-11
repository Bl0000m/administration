package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.dto.user.ResetCodeValidateRequestDto;

public interface UserResetCodeService {
    String getUserResetCode(String email);

    String generateCode();

    boolean isResetCodeExpired(ResetCodeValidateRequestDto resetCodeValidateRequestDto);

    void deleteUserResetCode(ResetCodeValidateRequestDto resetCodeValidateRequestDto);
}
