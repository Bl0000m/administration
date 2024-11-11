package kz.bloooom.administration.service;

import kz.bloooom.administration.domain.dto.user.ResetCodeValidateRequestDto;
import kz.bloooom.administration.domain.dto.user.UserResetCodeRequestDto;

public interface UserResetCodeService {
    String getUserResetCode(UserResetCodeRequestDto userResetCodeRequestDto);

    String generateCode();

    boolean isResetCodeExpired(ResetCodeValidateRequestDto resetCodeValidateRequestDto);

    void deleteUserResetCode(ResetCodeValidateRequestDto resetCodeValidateRequestDto);
}
