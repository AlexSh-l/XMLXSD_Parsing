package com.alex.xmlxsdparsing.parser.factory;

import com.alex.xmlxsdparsing.parser.DomParser;
import com.alex.xmlxsdparsing.parser.SaxParser;
import com.alex.xmlxsdparsing.parser.StaxParser;
import com.alex.xmlxsdparsing.parser.builder.TouristVouchersBuilder;

public class BuilderFactory {

    private enum ParserType{
        SAX,
        STAX,
        DOM
    }

    private BuilderFactory(){

    }

    public static TouristVouchersBuilder buildParser(String parserType) {
        ParserType type = ParserType.valueOf(parserType.toUpperCase());
        switch (type){
            case DOM -> {
                return new DomParser();
            }
            case SAX -> {
                return new SaxParser();
            }
            case STAX -> {
                return new StaxParser();
            }
            default -> throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }
    }
}
