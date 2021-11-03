package com.alex.xmlxsdparsing.validator.impl;

import com.alex.xmlxsdparsing.errorhandler.TouristVouchersErrorHandler;
import com.alex.xmlxsdparsing.exception.ValidationException;
import com.alex.xmlxsdparsing.validator.CustomXMLValidator;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XMLValidator implements CustomXMLValidator {

    @Override
    public boolean validateXML() throws ValidationException {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(XSD_SCHEMA_NAME);
        try {
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(XML_FILE_NAME);
            validator.setErrorHandler(new TouristVouchersErrorHandler());
            validator.validate(source);
        } catch (SAXException e) {
            return false;
        } catch (IOException e) {
            throw new ValidationException(e.getMessage(), e);
        }
        return true;
    }
}
