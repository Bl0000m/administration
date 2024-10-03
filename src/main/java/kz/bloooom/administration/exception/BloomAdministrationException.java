package kz.bloooom.administration.exception;

import kz.bloooom.administration.util.Bundle;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BloomAdministrationException extends RuntimeException {

    HttpStatus status;
    String code;

    public BloomAdministrationException(HttpStatus status, String code, String message, Object... objects) {
        super(Bundle.resolve(message, objects));
        this.status = status;
        this.code = code;
    }

    public BloomAdministrationException(String message, HttpStatus status, String code, Throwable cause) {
        super(message, cause);
        this.status = status;
        this.code = code;
    }

    public static Supplier<BloomAdministrationException> BloomAdministrationExceptionSupplier(
            HttpStatus status,
            String errorCodeConstant,
            String message,
            Object... objects
    ) {
        return () -> new BloomAdministrationException(
                status,
                errorCodeConstant,
                Bundle.resolve(message, objects)
        );
    }
}
