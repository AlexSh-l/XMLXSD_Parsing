package com.alex.xmlxsdparsing.parser.builder;

import com.alex.xmlxsdparsing.entity.TouristVoucher;
import com.alex.xmlxsdparsing.exception.ParserBuildVouchersException;

import java.util.HashSet;
import java.util.Set;

public abstract class TouristVouchersBuilder {

    private Set<TouristVoucher> vouchers;

    protected TouristVouchersBuilder() {
        vouchers = new HashSet<>();
    }

    protected void setVouchers(Set<TouristVoucher> vouchers) {
        this.vouchers = vouchers;
    }

    public Set<TouristVoucher> getVouchers() {
        return vouchers;
    }

    public abstract void buildSetVouchers(String filename) throws ParserBuildVouchersException;
}
