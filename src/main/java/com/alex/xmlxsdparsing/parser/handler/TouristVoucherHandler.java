package com.alex.xmlxsdparsing.parser.handler;

import com.alex.xmlxsdparsing.entity.Cost;
import com.alex.xmlxsdparsing.entity.Hotel;
import com.alex.xmlxsdparsing.entity.TouristVoucher;
import com.alex.xmlxsdparsing.entity.enumerationvalue.FoodType;
import com.alex.xmlxsdparsing.entity.enumerationvalue.VoucherType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class TouristVoucherHandler extends DefaultHandler {

    private static final Logger logger = LogManager.getLogger();
    private final Set<TouristVoucher> vouchers;
    private TouristVoucher voucher;
    private String currentXmlTag;
    private Hotel hotel;
    private Cost cost;

    public TouristVoucherHandler() {
        vouchers = new HashSet<>();
    }

    public Set<TouristVoucher> getVouchers() {
        return vouchers;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if (qName.equals("c:tourist-voucher")) {
            voucher = new TouristVoucher();
            voucher.setId(attrs.getValue("id"));
            String voucherName = attrs.getValue("name");
            if (voucherName != null) {
                voucher.setName(voucherName);
            }
        } else {
            currentXmlTag = qName;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("c:tourist-voucher")) {
            voucher.addHotel(hotel);
            hotel = null;
            voucher.setCost(cost);
            cost = null;
            vouchers.add(voucher);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).strip();
        if (currentXmlTag != null) {
            switch (currentXmlTag) {
                case "Voucher-type" -> {
                    VoucherType voucherType = VoucherType.valueOf(data);
                    voucher.setVoucherType(voucherType);
                }
                case "Country" -> voucher.addCountry(data);
                case "Number-days" -> {
                    int numberDays = Integer.parseInt(data);
                    voucher.setNumberDays(numberDays);
                }
                case "Number-nights" -> {
                    int numberNights = Integer.parseInt(data);
                    voucher.setNumberNights(numberNights);
                }
                case "Start-date" -> {
                    LocalDateTime startDate = LocalDateTime.parse(data);
                    voucher.setDateTime(startDate);
                }
                case "Transport" -> voucher.addTransport(data);
                case "Hotel" -> {
                    if (hotel != null) {
                        voucher.addHotel(hotel);
                    }
                    hotel = new Hotel();
                }
                case "name" -> hotel.setName(data);
                case "stars" -> {
                    short hotelStars = Short.parseShort(data);
                    hotel.setStars(hotelStars);
                }
                case "is-food-included" -> {
                    boolean hotelIsFoodIncluded = Boolean.parseBoolean(data);
                    hotel.setFoodIncluded(hotelIsFoodIncluded);
                }
                case "food" -> {
                    FoodType hotelFoodType = FoodType.valueOf(data);
                    hotel.setFoodType(hotelFoodType);
                }
                case "amount-of-rooms" -> {
                    short hotelAmountOfRooms = Short.parseShort(data);
                    hotel.setAmountOfRooms(hotelAmountOfRooms);
                }
                case "is-tv-included" -> {
                    boolean hotelIsTVIncluded = Boolean.parseBoolean(data);
                    hotel.setTVIncluded(hotelIsTVIncluded);
                }
                case "is-conditioner-included" -> {
                    boolean hotelIsConditionerIncluded = Boolean.parseBoolean(data);
                    hotel.setConditionerIncluded(hotelIsConditionerIncluded);
                }
                case "Cost" -> cost = new Cost();
                case "cost" -> {
                    double costCost = Double.parseDouble(data);
                    cost.setCost(costCost);
                }
                case "includes" -> cost.addIncludes(data);
                default -> {
                    if (!currentXmlTag.equals("TouristVouchers")) {
                        logger.error("Unknown xml tag");
                    }
                }
            }
        }
        currentXmlTag = null;
    }
}
