package com.alex.xmlxsdparsing.exception;

public class ParserBuildVouchersException extends Exception {

    public ParserBuildVouchersException(String message) {
        super(message);
    }

    public ParserBuildVouchersException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParserBuildVouchersException(Throwable cause) {
        super(cause);
    }

    protected ParserBuildVouchersException(String message, Throwable cause,
                                           boolean enableSuppression,
                                           boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
