package com.alex.xmlxsdparsing;

import com.alex.xmlxsdparsing.entity.TouristVoucher;
import com.alex.xmlxsdparsing.exception.*;
import com.alex.xmlxsdparsing.parser.builder.TouristVouchersBuilder;
import com.alex.xmlxsdparsing.parser.factory.BuilderFactory;
import com.alex.xmlxsdparsing.validator.impl.XMLValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        String xmlFileName = "data/tourist-vouchers.xml";
        XMLValidator validator = new XMLValidator();
        try {
            logger.info(validator.validateXML());
        } catch (ValidationException e) {
            logger.error(e.getMessage(), e);
        }
        TouristVouchersBuilder builder = BuilderFactory.buildParser("dom");
        try {
            builder.buildSetVouchers(xmlFileName);
        } catch (ParserBuildVouchersException e) {
            logger.error(e.getMessage(), e);
        }
        Set<TouristVoucher> domVouchers = builder.getVouchers();
        logger.info(domVouchers.toString());
        builder = BuilderFactory.buildParser("stax");
        try {
            builder.buildSetVouchers(xmlFileName);
        } catch (ParserBuildVouchersException e) {
            logger.error(e.getMessage(), e);
        }
        Set<TouristVoucher> staxVouchers = builder.getVouchers();
        logger.info(staxVouchers.toString());
        builder = BuilderFactory.buildParser("sax");
        try {
            builder.buildSetVouchers(xmlFileName);
        } catch (ParserBuildVouchersException e) {
            logger.error(e.getMessage(), e);
        }
        Set<TouristVoucher> saxVouchers = builder.getVouchers();
        logger.info(saxVouchers.toString());
    }
}
