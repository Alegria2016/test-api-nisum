package com.testnisum.api_rest.utils;

public class Constant {
    public static final boolean IS_ACTIVE_TRUE = true;
    public static final String USER_ROLE = "USER";

    public static final String SECRET_KEY = "dGhpcyBpcyBteSBzZWN1cmUga2V5IGFuZCB5b3UgY2Fubm90IGhhY2sgaXQ=";

    public static final int EXPIRATION_MINUTES = 30;


    public static final String REGEX_EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

    public static final String REGEX_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,20}$";
}
