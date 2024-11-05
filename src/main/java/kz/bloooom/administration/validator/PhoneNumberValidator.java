package kz.bloooom.administration.validator;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.exception.BloomAdministrationException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PhoneNumberValidator {

    static String KZ_NUMBER_PATTERN = "^(\\+)?7\\d{10}$";
    List<PhoneNumberChecker> phoneNumberCheckers;


    public void checkValid(String target) {
        if (BooleanUtils.isFalse(target.matches(KZ_NUMBER_PATTERN))) {
            throw new BloomAdministrationException(
                    HttpStatus.BAD_REQUEST,
                    ErrorCodeConstant.WRONG_NUMBER_FORMAT,
                    "messages.exception.invalid-phone-number-format", target);
        }
        if (!target.isBlank() && !target.equals("")) {
            for (PhoneNumberChecker phoneNumberChecker : phoneNumberCheckers) {
                if (phoneNumberChecker.existsPhoneNumber(target)) {
                    throw new BloomAdministrationException(
                            HttpStatus.BAD_REQUEST,
                            ErrorCodeConstant.USER_PHONE_NUMBER_ALREADY_EXIST,
                            "messages.exception.user-phone-number-already-exist", target);
                }
            }
        }
    }
}
