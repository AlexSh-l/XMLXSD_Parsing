package com.alex.xmlxsdparsing.parser;

import com.alex.xmlxsdparsing.entity.Cost;
import com.alex.xmlxsdparsing.entity.Hotel;
import com.alex.xmlxsdparsing.entity.TouristVoucher;
import com.alex.xmlxsdparsing.entity.enumvalue.FoodType;
import com.alex.xmlxsdparsing.entity.enumvalue.VoucherType;
import com.alex.xmlxsdparsing.exception.ParserBuildVouchersException;
import com.alex.xmlxsdparsing.parser.builder.TouristVouchersBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class StaxParser extends TouristVouchersBuilder {

    private static final Logger logger = LogManager.getLogger();
    private final Set<TouristVoucher> vouchers;
    private final XMLInputFactory inputFactory;

    public StaxParser() {
        inputFactory = XMLInputFactory.newInstance();
        vouchers = new HashSet<>();
    }

    public Set<TouristVoucher> getVouchers() {
        return vouchers;
    }

    @Override
    public void buildSetVouchers(String filename) throws ParserBuildVouchersException {
        XMLStreamReader reader;
        String name;
        try (FileInputStream inputStream = new FileInputStream(new File(filename))) {
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals("tourist-voucher")) {
                        TouristVoucher voucher = buildVoucher(reader);
                        vouchers.add(voucher);
                    }
                }
            }
            super.setVouchers(vouchers);
        } catch (XMLStreamException | FileNotFoundException e) {
            logger.error(e.getMessage(), e);
            throw new ParserBuildVouchersException(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new ParserBuildVouchersException(e.getMessage(), e);
        }
    }

    private TouristVoucher buildVoucher(XMLStreamReader reader) throws XMLStreamException {
        TouristVoucher voucher = new TouristVoucher();
        String voucherId = reader.getAttributeValue(null, "id");
        voucher.setId(voucherId);
        String voucherName = reader.getAttributeValue(null, "name");
        if (voucherName != null) {
            voucher.setName(voucherName);
        }
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (name) {
                        case "Voucher-type" -> {
                            VoucherType voucherType = VoucherType.valueOf(getXMLText(reader));
                            voucher.setVoucherType(voucherType);
                        }
                        case "Country" -> {
                            String country = getXMLText(reader);
                            voucher.addCountry(country);
                        }
                        case "Number-days" -> {
                            int numberDays = Integer.parseInt(getXMLText(reader));
                            voucher.setNumberDays(numberDays);
                        }
                        case "Number-nights" -> {
                            int numberNights = Integer.parseInt(getXMLText(reader));
                            voucher.setNumberNights(numberNights);
                        }
                        case "Start-date" -> {
                            LocalDateTime startDate = LocalDateTime.parse(getXMLText(reader));
                            voucher.setDateTime(startDate);
                        }
                        case "Transport" -> {
                            String transport = getXMLText(reader);
                            voucher.addTransport(transport);
                        }
                        case "Hotel" -> {
                            Hotel hotel = getXMLHotel(reader);
                            voucher.addHotel(hotel);
                        }
                        case "Cost" -> {
                            Cost cost = getXMLCost(reader);
                            voucher.setCost(cost);
                        }
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (name.equals("tourist-voucher")) {
                        return voucher;
                    }
            }
        }
        throw new XMLStreamException("Unknown element in tag <tourist-voucher>");
    }

    private Hotel getXMLHotel(XMLStreamReader reader) throws XMLStreamException {
        Hotel hotel = new Hotel();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (name) {
                        case "name" -> {
                            String hotelName = getXMLText(reader);
                            hotel.setName(hotelName);
                        }
                        case "stars" -> {
                            short hotelStars = Short.parseShort(getXMLText(reader));
                            hotel.setStars(hotelStars);
                        }
                        case "is-food-included" -> {
                            boolean hotelIsFoodIncluded = Boolean.parseBoolean(getXMLText(reader));
                            hotel.setFoodIncluded(hotelIsFoodIncluded);
                        }
                        case "food" -> {
                            FoodType hotelFoodType = FoodType.valueOf(getXMLText(reader));
                            hotel.setFoodType(hotelFoodType);
                        }
                        case "amount-of-rooms" -> {
                            short hotelAmountOfRooms = Short.parseShort(getXMLText(reader));
                            hotel.setAmountOfRooms(hotelAmountOfRooms);
                        }
                        case "is-tv-included" -> {
                            boolean hotelIsTVIncluded = Boolean.parseBoolean(getXMLText(reader));
                            hotel.setTVIncluded(hotelIsTVIncluded);
                        }
                        case "is-conditioner-included" -> {
                            boolean hotelIsConditionerIncluded = Boolean.parseBoolean(getXMLText(reader));
                            hotel.setConditionerIncluded(hotelIsConditionerIncluded);
                        }
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (name.equals("Hotel")) {
                        return hotel;
                    }
            }
        }
        throw new XMLStreamException("Unknown element in tag <Hotel>");
    }

    private Cost getXMLCost(XMLStreamReader reader) throws XMLStreamException {
        Cost cost = new Cost();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (name) {
                        case "cost" -> {
                            double costCost = Double.parseDouble(getXMLText(reader));
                            cost.setCost(costCost);
                        }
                        case "includes" -> {
                            String costIncludes = getXMLText(reader);
                            cost.addIncludes(costIncludes);
                        }
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (name.equals("Cost")) {
                        return cost;
                    }
            }
        }
        throw new XMLStreamException("Unknown element in tag <Cost>");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
