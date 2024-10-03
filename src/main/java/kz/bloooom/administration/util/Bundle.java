package kz.bloooom.administration.util;

import kz.bloooom.administration.service.I18nService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Bundle {

    private static Bundle bundle;
    private final I18nService i18nService;

    public Bundle(ApplicationContext context) {
        i18nService = context.getBean(I18nService.class);
        bundle = this;
    }

    public static String resolve(String code, Object... objects) {
        return bundle.i18nService.getMessage(code, objects);
    }
}
