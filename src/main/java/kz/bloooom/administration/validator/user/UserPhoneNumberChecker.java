package kz.bloooom.administration.validator.user;

import kz.bloooom.administration.service.UserService;
import kz.bloooom.administration.validator.PhoneNumberChecker;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserPhoneNumberChecker implements PhoneNumberChecker {

    UserService userService;

    @Override
    public boolean existsPhoneNumber(String phoneNumber) {
        return userService.existsByPhoneNumber(phoneNumber);
    }
}

