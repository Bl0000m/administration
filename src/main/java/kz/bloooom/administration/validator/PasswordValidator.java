package kz.bloooom.administration.validator;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.domain.dto.user.UserRegistrationDto;
import kz.bloooom.administration.exception.BloomAdministrationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class PasswordValidator {
    public void checkValid(UserRegistrationDto target) {
        if (!StringUtils.equals(target.getPassword(), target.getConfirmPassword())) {
            throw new BloomAdministrationException(
                    HttpStatus.BAD_REQUEST,
                    ErrorCodeConstant.PASSWORD_NOT_MATCH,
                    "messages.exception.password-not-match");
        }
    }
}
