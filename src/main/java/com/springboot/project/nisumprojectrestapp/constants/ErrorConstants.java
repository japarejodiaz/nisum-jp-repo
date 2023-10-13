package com.springboot.project.nisumprojectrestapp.constants;


/**
 * Enumerated Type ErrorConstants
 *
 * Defines common error keys used across the service
 */
public enum ErrorConstants {


    ERROR_TEST_1 (10020001, "error.statement.description.list.notfound"),
    ERROR_TEST_2 (10020014, "error.help.description.list.notfound");

    private final String message;

    private final long code;

    ErrorConstants(long code, String message) {
        this.code = code;
        this.message = message;

    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return this.message;
    }
}
