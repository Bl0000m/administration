package kz.bloooom.administration.validator;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.domain.dto.user.ForgotPasswordRequestDto;
import kz.bloooom.administration.exception.BloomAdministrationException;
import kz.bloooom.administration.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ForgotPasswordValidator {
    UserService userService;

    public void validate(ForgotPasswordRequestDto target) {
        if (!userService.existsByEmailAndNotDelete(target.getEmail())) {
            throw new BloomAdministrationException(
                    HttpStatus.BAD_REQUEST,
                    ErrorCodeConstant.USER_WITH_THIS_ID_DOEST_EXISTS,
                    "messages.exception.user-with-this-email-not-found", target.getEmail());
        }
        if (!StringUtils.equals(target.getNewPassword(), target.getConfirmNewPassword())) {
            throw new BloomAdministrationException(
                    HttpStatus.BAD_REQUEST,
                    ErrorCodeConstant.PASSWORD_NOT_MATCH,
                    "messages.exception.password-not-match");
        }
    }
}
