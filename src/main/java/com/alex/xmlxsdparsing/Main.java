package com.alex.xmlxsdparsing;

import com.alex.xmlxsdparsing.exception.ValidationException;
import com.alex.xmlxsdparsing.parser.DomParser;
import com.alex.xmlxsdparsing.validator.impl.XMLValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        XMLValidator validator = new XMLValidator();
        try {
            logger.info(validator.validateXML());
        }catch (ValidationException e){
            logger.error(e.getMessage(), e);
        }

        DomParser domParser = new DomParser();
        domParser.buildSetVouchers("data/tourist-vouchers.xml");
    }
}
