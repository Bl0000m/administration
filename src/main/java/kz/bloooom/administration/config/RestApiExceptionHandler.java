package kz.bloooom.administration.config;

import kz.bloooom.administration.exception.BloomAdministrationException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class RestApiExceptionHandler {

    @ExceptionHandler(BloomAdministrationException.class)
    protected ResponseEntity<ErrorResponseDto> handleHttpError(BloomAdministrationException e,
                                                               HttpServletRequest httpServletRequest) {
        String message = getMessage(httpServletRequest);
        ErrorResponseDto body = new ErrorResponseDto(e);
        log.error(body.id + ": " + message, e);
        return ResponseEntity.status(e.getStatus())
                .body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleInternalError(Exception e,
                                                      HttpServletRequest httpServletRequest) {
        String message = getMessage(httpServletRequest);
        ErrorResponseDto body = new ErrorResponseDto(e);
        log.error(body.id + ": " + message, e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body);
    }

    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            HttpRequestMethodNotSupportedException.class,
            MissingServletRequestParameterException.class
    })
    public ResponseEntity<Object> handleBadRequest(Exception e,
                                                   HttpServletRequest httpServletRequest) {
        String message = getMessage(httpServletRequest);
        ErrorResponseDto body = new ErrorResponseDto(e);
        log.warn(body.id + ": " + message, e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(
            MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
                .body(getErrorsMap(errors));
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }


    private String getMessage(HttpServletRequest httpServletRequest) {
        return "Запрос ручки " + httpServletRequest.getRequestURI() + " завершился провалом:";
    }

    @Getter
    @AllArgsConstructor
    public static class ErrorResponseDto {

        private final String id = UUID.randomUUID().toString().substring(0, 8);
        private final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        private String type;
        private String code;
        private String message;

        ErrorResponseDto(Exception e) {
            message = e.getMessage();
            type = e.getClass().getSimpleName();
        }

        ErrorResponseDto(BloomAdministrationException e) {
            message = e.getMessage();
            code = e.getCode();
            type = e.getClass().getSimpleName();
        }
    }
}
