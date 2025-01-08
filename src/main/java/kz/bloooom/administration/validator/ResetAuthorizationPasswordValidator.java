package kz.bloooom.administration.validator;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.domain.dto.employee.ResetUserAuthorizationRequestDto;
import kz.bloooom.administration.exception.BloomAdministrationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ResetAuthorizationPasswordValidator {

    public void checkValid(ResetUserAuthorizationRequestDto target) {
        if (!StringUtils.equals(target.getNewPassword(), target.getConfirmNewPassword())) {
            throw new BloomAdministrationException(
                    HttpStatus.BAD_REQUEST,
                    ErrorCodeConstant.PASSWORDS_NOT_SAME,
                    "messages.exception.password-not-same");
        }
    }
}
