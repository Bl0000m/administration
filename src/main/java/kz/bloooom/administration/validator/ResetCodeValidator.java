package kz.bloooom.administration.validator;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.domain.dto.user.ResetCodeValidateRequestDto;
import kz.bloooom.administration.exception.BloomAdministrationException;
import kz.bloooom.administration.service.UserResetCodeService;
import kz.bloooom.administration.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ResetCodeValidator  {

    UserService userService;
    UserResetCodeService userResetCodeService;

    public void validate(ResetCodeValidateRequestDto target) {
        if (!userService.existsByEmailAndNotDelete(target.getEmail())) {
            throw new BloomAdministrationException(
                    HttpStatus.BAD_REQUEST,
                    ErrorCodeConstant.USER_WITH_THIS_ID_DOEST_EXISTS,
                    "messages.exception.user-with-this-email-not-found", target.getEmail());
        }

        if (userResetCodeService.isResetCodeExpired(target)) {
            userResetCodeService.deleteUserResetCode(target);
            throw new BloomAdministrationException(
                    HttpStatus.BAD_REQUEST,
                    ErrorCodeConstant.RESET_CODE_EXPIRED,
                    "messages.exception.recovery-code-expired", target.getResetCode());
        }
    }
}