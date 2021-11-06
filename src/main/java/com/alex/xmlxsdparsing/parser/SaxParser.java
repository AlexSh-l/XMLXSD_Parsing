package com.alex.xmlxsdparsing.parser;

import com.alex.xmlxsdparsing.entity.TouristVoucher;
import com.alex.xmlxsdparsing.parser.errorhandler.TouristVouchersErrorHandler;
import com.alex.xmlxsdparsing.exception.ParserBuildVouchersException;
import com.alex.xmlxsdparsing.parser.builder.TouristVouchersBuilder;
import com.alex.xmlxsdparsing.parser.handler.TouristVoucherHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Set;

public class SaxParser extends TouristVouchersBuilder {

    private static final Logger logger = LogManager.getLogger();
    private Set<TouristVoucher> vouchers;
    private final TouristVoucherHandler handler = new TouristVoucherHandler();
    private XMLReader reader;

    public SaxParser() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException | SAXException e) {
            logger.error(e.getMessage(), e);
        }
        reader.setErrorHandler(new TouristVouchersErrorHandler());
        reader.setContentHandler(handler);
    }

    public Set<TouristVoucher> getVouchers() {
        return vouchers;
    }

    @Override
    public void buildSetVouchers(String filename) throws ParserBuildVouchersException {
        try {
            reader.parse(filename);
        } catch (IOException | SAXException e) {
            logger.error(e.getMessage(), e);
            throw new ParserBuildVouchersException(e.getMessage(), e);
        }
        vouchers = handler.getVouchers();
        super.setVouchers(vouchers);
    }
}
