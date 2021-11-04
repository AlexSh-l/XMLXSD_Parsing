package com.alex.xmlxsdparsing.parser;

public enum TagEnum {
    VOUCHER_TYPE ("Voucher-type"),
    COUNTRY ("Country"),

    ;

    private String value;

    TagEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
