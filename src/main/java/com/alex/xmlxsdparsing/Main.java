package com.alex.xmlxsdparsing;

import com.alex.xmlxsdparsing.entity.TouristVoucher;
import com.alex.xmlxsdparsing.exception.DomParserBuildVouchersException;
import com.alex.xmlxsdparsing.exception.StaxParserBuildVouchersException;
import com.alex.xmlxsdparsing.exception.ValidationException;
import com.alex.xmlxsdparsing.parser.DomParser;
import com.alex.xmlxsdparsing.parser.StaxParser;
import com.alex.xmlxsdparsing.validator.impl.XMLValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        XMLValidator validator = new XMLValidator();
        try {
            logger.info(validator.validateXML());
        } catch (ValidationException e) {
            logger.error(e.getMessage(), e);
        }

        DomParser domParser = new DomParser();
        try {
            domParser.buildSetVouchers("data/tourist-vouchers.xml");
        } catch (DomParserBuildVouchersException e) {
            logger.error(e.getMessage(), e);
        }
        Set<TouristVoucher> domVouchers = domParser.getVouchers();
        domVouchers.toString();

        StaxParser staxParser = new StaxParser();
        try{
            staxParser.buildSetVouchers("data/tourist-vouchers.xml");
        }catch (StaxParserBuildVouchersException e){
            logger.error(e.getMessage(), e);
        }
        Set<TouristVoucher> staxVouchers = staxParser.getVouchers();
        staxVouchers.toString();
    }
}
