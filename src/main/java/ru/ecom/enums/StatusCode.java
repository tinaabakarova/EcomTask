package ru.ecom.enums;

public enum StatusCode {
    OK(200),
    BAD_REQUEST(400),
    METHOD_NOT_ALLOWED(405);

    private Integer code;

    StatusCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
