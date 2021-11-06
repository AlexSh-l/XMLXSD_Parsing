package com.alex.xmlxsdparsing.exception;

public class SaxParserBuildVouchersException extends Exception {

    public SaxParserBuildVouchersException(String message) {
        super(message);
    }

    public SaxParserBuildVouchersException(String message, Throwable cause) {
        super(message, cause);
    }

    public SaxParserBuildVouchersException(Throwable cause) {
        super(cause);
    }

    protected SaxParserBuildVouchersException(String message, Throwable cause,
                                              boolean enableSuppression,
                                              boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
