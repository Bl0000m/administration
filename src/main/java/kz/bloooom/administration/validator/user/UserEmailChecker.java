package kz.bloooom.administration.validator.user;

import kz.bloooom.administration.service.UserService;
import kz.bloooom.administration.validator.EmailChecker;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserEmailChecker implements EmailChecker {

    UserService userService;

    @Override
    public boolean emailExists(String email) {
        return userService.existsByEmail(email);
    }
}

