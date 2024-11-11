package kz.bloooom.administration.service.impl;

import kz.bloooom.administration.contant.ErrorCodeConstant;
import kz.bloooom.administration.domain.dto.user.ResetCodeValidateRequestDto;
import kz.bloooom.administration.domain.dto.user.UserResetCodeRequestDto;
import kz.bloooom.administration.domain.entity.UserResetCode;
import kz.bloooom.administration.exception.BloomAdministrationException;
import kz.bloooom.administration.repository.UserResetCodeRepository;
import kz.bloooom.administration.service.UserResetCodeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserResetCodeServiceImpl implements UserResetCodeService {

    static int RESET_CODE_EXPIRATION_TIME = 1;
    UserResetCodeRepository userResetCodeRepository;


    @Override
    @Transactional
    public String getUserResetCode(String email) {
        String generateResetCode = generateCode();

        UserResetCode userResetCode = UserResetCode.builder()
                .email(email)
                .resetCode(generateResetCode)
                .createdDate(new Timestamp(System.currentTimeMillis()))
                .updatedDate(new Timestamp(System.currentTimeMillis()))
                .build();
        userResetCodeRepository.save(userResetCode);
        return generateResetCode;
    }

    @Override
    public String generateCode() {
        Set<Integer> digits = new HashSet<>();
        while (digits.size() < 4) {
            digits.add((int) (Math.random() * 10));
        }
        return String.join("", digits.stream().map(String::valueOf).toArray(String[]::new));
    }

    @Override
    public boolean isResetCodeExpired(ResetCodeValidateRequestDto resetCodeValidateRequestDto) {
        log.info("UserResetCodeServiceImpl | reset pass-code | email = {}", resetCodeValidateRequestDto.getEmail());
        UserResetCode userResetCode = findUserResetCode(resetCodeValidateRequestDto);
        Duration diff = Duration.between(userResetCode.getCreatedDate().toLocalDateTime(), LocalDateTime.now());
        return diff.toMinutes() >= RESET_CODE_EXPIRATION_TIME;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteUserResetCode(ResetCodeValidateRequestDto resetCodeValidateRequestDto) {
        UserResetCode userResetCode = findUserResetCode(resetCodeValidateRequestDto);
        userResetCodeRepository.delete(userResetCode);
    }

    private UserResetCode findUserResetCode(
            ResetCodeValidateRequestDto resetCodeValidateRequestDto) {
        return userResetCodeRepository.findByEmailAndResetCode(
                        resetCodeValidateRequestDto.getEmail(),
                        resetCodeValidateRequestDto.getResetCode())
                .orElseThrow(() -> new BloomAdministrationException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        ErrorCodeConstant.RESET_CODE_INVALID,
                        "messages.exception.reset-code-invalid"));
    }
}
