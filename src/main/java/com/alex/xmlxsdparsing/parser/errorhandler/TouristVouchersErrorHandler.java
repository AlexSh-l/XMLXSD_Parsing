package com.alex.xmlxsdparsing.parser.errorhandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class TouristVouchersErrorHandler implements ErrorHandler {

    private static final Logger logger = LogManager.getLogger();

    public void warning(SAXParseException exception) throws SAXException {
        logger.warn(exception.getLineNumber() + " : " + exception.getColumnNumber() + "-" + exception.getMessage());
    }

    public void error(SAXParseException exception) throws SAXException {
        logger.error(exception.getLineNumber() + " : " + exception.getColumnNumber() + "-" + exception.getMessage());
    }

    public void fatalError(SAXParseException exception) throws SAXException {
        logger.fatal(exception.getLineNumber() + " : " + exception.getColumnNumber() + "-" + exception.getMessage());
    }
}
