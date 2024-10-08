package kz.bloooom.administration.contant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorCodeConstant {
    public final String USER_WITH_THIS_ID_DOEST_EXISTS = "USER_WITH_THIS_ID_DOEST_EXISTS";
    public final String USER_EMAIL_ALREADY_EXIST = "USER_EMAIL_ALREADY_EXIST";
    public final String WRONG_NUMBER_FORMAT = "WRONG_NUMBER_FORMAT";
    public final String USER_PHONE_NUMBER_ALREADY_EXIST = "USER_PHONE_NUMBER_ALREADY_EXIST";
    public final String WRONG_LOGIN_OR_PASSWORD = "WRONG_LOGIN_OR_PASSWORD";
    public final String CREATE_KEYCLOAK_USER_ERROR = "CREATE_KEYCLOAK_USER_ERROR";
    public final String REFRESH_TOKEN_INCORRECT = "REFRESH_TOKEN_INCORRECT";
    public final String USERNAME_INCORRECT = "USERNAME_INCORRECT";
    public final String USER_WITH_THIS_EMAIL_NOT_FOUND = "USER_WITH_THIS_EMAIL_NOT_FOUND";
    public final String ROLE_WITH_THIS_ID_DOEST_EXISTS = "ROLE_WITH_THIS_ID_DOEST_EXISTS";
    public final String ROLE_WITH_THIS_CODE_DOEST_EXISTS = "ROLE_WITH_THIS_CODE_DOEST_EXISTS";
    public final String USER_NOT_FOUNT = "USER_NOT_FOUNT";
    public final String PASSWORD_NOT_MATCH = "PASSWORD_NOT_MATCH";
}
