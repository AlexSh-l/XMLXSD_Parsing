package com.alex.xmlxsdparsing.exception;

public class StaxParserBuildVouchersException extends Exception {

    public StaxParserBuildVouchersException(String message) {
        super(message);
    }

    public StaxParserBuildVouchersException(String message, Throwable cause) {
        super(message, cause);
    }

    public StaxParserBuildVouchersException(Throwable cause) {
        super(cause);
    }

    protected StaxParserBuildVouchersException(String message, Throwable cause,
                                              boolean enableSuppression,
                                              boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
