package com.example.demo_jwt.enums;

import lombok.Getter;

@Getter
public enum ResponseCodeEnum {
    SUCCESS(0, "Success"),
    ERROR(-1, "An error occurred. Error message : ${errorMessage}"),
    ;

    ResponseCodeEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    private final int code;
    private final String description;

}
