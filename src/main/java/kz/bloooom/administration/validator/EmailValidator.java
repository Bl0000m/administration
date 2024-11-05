package kz.bloooom.administration.validator;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.exception.BloomAdministrationException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailValidator {

    List<EmailChecker> emailCheckers;

    public void checkValid(String email) {
        for (EmailChecker emailChecker : emailCheckers) {
            if (emailChecker.emailExists(email)) {
                String checkerName = emailChecker.getClass().getSimpleName();
                throw new BloomAdministrationException(
                        HttpStatus.BAD_REQUEST,
                        ErrorCodeConstant.USER_EMAIL_ALREADY_EXIST,
                        "messages.exception." + checkerName.toLowerCase(), email);
            }
        }
    }
}

