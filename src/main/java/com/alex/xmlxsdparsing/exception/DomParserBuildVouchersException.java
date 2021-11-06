package com.alex.xmlxsdparsing.exception;

public class DomParserBuildVouchersException extends Exception {

    public DomParserBuildVouchersException(String message) {
        super(message);
    }

    public DomParserBuildVouchersException(String message, Throwable cause) {
        super(message, cause);
    }

    public DomParserBuildVouchersException(Throwable cause) {
        super(cause);
    }

    protected DomParserBuildVouchersException(String message, Throwable cause,
                                              boolean enableSuppression,
                                              boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
