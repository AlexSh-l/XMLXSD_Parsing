package com.alex.xmlxsdparsing.validator;

import com.alex.xmlxsdparsing.exception.ValidationException;

public interface CustomXMLValidator {

    String XML_FILE_NAME = "data/tourist-vouchers.xml";

    String XSD_SCHEMA_NAME = "data/schema.xsd";

    boolean validateXML() throws ValidationException;
}
