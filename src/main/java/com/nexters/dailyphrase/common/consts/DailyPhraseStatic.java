package com.nexters.dailyphrase.common.consts;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DailyPhraseStatic {
    public static final String AUTH_HEADER = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String NULL_STRING = "null";
    public static final String WITHDRAW_PREFIX = "DELETED:";
    public static final String TOKEN_ROLE = "role";
    public static final String TOKEN_TYPE = "type";
    public static final String TOKEN_ISSUER = "DailyPhrase";
    public static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    public static final String REFRESH_TOKEN = "REFRESH_TOKEN";

    public static final int MILLI_TO_SECOND = 1000;
    public static final int BAD_REQUEST = 400;
    public static final int UNAUTHORIZED = 401;
    public static final int FORBIDDEN = 403;
    public static final int NOT_FOUND = 404;
    public static final int DUPLICATE = 409;
    public static final int TOO_MANY_REQUESTS = 429;
    public static final int INTERNAL_SERVER = 500;

    public static final int MAX_EVENT_TICKETS_PER_DAY = 10;
    public static final Long CURRENT_ACTIVE_EVENT_ID = 1L;

    public static final int SIGN_UP_TICKET_COUNT = 5;
}
