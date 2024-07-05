package com.danhpahm.identity_service.exception;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error"),
    USER_EXISTED(1002, "user existed"),
    INVALID_KEY(1001, "invalid message key"),
    USERNAME_INVALID(1003,"username must be at least 3 characters"),
    PASSWORD_INVALID(1004, "password must be at least 8 characters"),
    USER_NOT_EXISTED(1005, "user not existed"),
    UNAUTHENTICATED(1006, "unauthenticated"),
    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
