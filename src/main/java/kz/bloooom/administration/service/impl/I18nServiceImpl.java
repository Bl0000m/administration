package kz.bloooom.administration.service.impl;

import kz.bloooom.administration.service.I18nService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class I18nServiceImpl implements I18nService {
    MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage(String code, Object... objects) {
        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage(code, null,
                "Текст отсутсвует. Обратитесь в техподдержку", locale);
        if (objects != null && objects.length > 0) {
            if (StringUtils.isEmpty(message)) {
                throw new IllegalStateException("message must not be empty or null");
            }
            return String.format(message, objects);
        } else {
            return message;
        }
    }
}
