package com.alex.xmlxsdparsing.parser;

import com.alex.xmlxsdparsing.entity.Cost;
import com.alex.xmlxsdparsing.entity.Hotel;
import com.alex.xmlxsdparsing.entity.TouristVoucher;
import com.alex.xmlxsdparsing.entity.enumerationvalue.FoodType;
import com.alex.xmlxsdparsing.entity.enumerationvalue.VoucherType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class DomParser {

    private final Set<TouristVoucher> vouchers;
    private DocumentBuilder docBuilder;
    private static final Logger logger = LogManager.getLogger();

    public DomParser() {
        vouchers = new HashSet<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public Set<TouristVoucher> getVouchers() {
        return vouchers;
    }

    public void buildSetVouchers(String filename) {
        Document doc;
        try {
            doc = docBuilder.parse(filename);
            Node root = doc.getDocumentElement();
            NodeList touristVouchers = root.getChildNodes();
            for (int i = 0; i < touristVouchers.getLength(); i++) {
                Node voucherNode = touristVouchers.item(i);
                if (voucherNode.getNodeName().equals("c:tourist-voucher")) {
                    TouristVoucher voucher = buildVoucher(voucherNode);
                    vouchers.add(voucher);
                }
            }
        } catch (IOException | SAXException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private TouristVoucher buildVoucher(Node voucherElement) {
        TouristVoucher voucher = new TouristVoucher();
        String voucherId = voucherElement.getAttributes().getNamedItem("id").getTextContent();
        voucher.setId(voucherId);
        Node voucherNameNode = voucherElement.getAttributes().getNamedItem("name");
        if (voucherNameNode != null) {
            voucher.setName(voucherNameNode.getTextContent());
        }
        List<String> voucherType = getElementTextContent(voucherElement, "Voucher-type");
        VoucherType voucherTypeEnum = VoucherType.valueOf(voucherType.get(0));
        voucher.setVoucherType(voucherTypeEnum);
        List<String> countryList = getElementTextContent(voucherElement, "Country");
        for (String country : countryList) {
            voucher.addCountry(country);
        }
        List<String> numberDays = getElementTextContent(voucherElement, "Number-days");
        voucher.setNumberDays(Integer.parseInt(numberDays.get(0)));
        List<String> numberNights = getElementTextContent(voucherElement, "Number-nights");
        voucher.setNumberNights(Integer.parseInt(numberNights.get(0)));
        List<String> startDate = getElementTextContent(voucherElement, "Start-date");
        LocalDateTime localDateTime = LocalDateTime.parse(startDate.get(0));
        voucher.setDateTime(localDateTime);
        List<String> transportList = getElementTextContent(voucherElement, "Transport");
        voucher.setTransport(transportList);
        int hotelCount = countElements(voucherElement, "Hotel");
        List<String> hotelName = getSubElementTextContent(voucherElement, "Hotel", "name");
        List<String> hotelStars = getSubElementTextContent(voucherElement, "Hotel", "stars");
        List<String> hotelIsFoodIncluded = getSubElementTextContent(voucherElement, "Hotel", "is-food-included");
        List<String> hotelFood = getSubElementTextContent(voucherElement, "Hotel", "food");
        List<String> hotelAmountOfRooms = getSubElementTextContent(voucherElement, "Hotel", "amount-of-rooms");
        List<String> hotelIsTVIncluded = getSubElementTextContent(voucherElement, "Hotel", "is-tv-included");
        List<String> hotelIsConditionerIncluded = getSubElementTextContent(voucherElement, "Hotel", "is-conditioner-included");
        for (int i = 0; i < hotelCount; i++) {
            if (Boolean.parseBoolean(hotelIsFoodIncluded.get(i))) {
                Hotel hotel = new Hotel(hotelName.get(i), Short.parseShort(hotelStars.get(i)), Boolean.parseBoolean(hotelIsFoodIncluded.get(i)), FoodType.valueOf(hotelFood.get(i)), Short.parseShort(hotelAmountOfRooms.get(i)), Boolean.parseBoolean(hotelIsTVIncluded.get(i)), Boolean.parseBoolean(hotelIsConditionerIncluded.get(i)));
                voucher.addHotel(hotel);
            } else {
                Hotel hotel = new Hotel(hotelName.get(i), Short.parseShort(hotelStars.get(i)), Boolean.parseBoolean(hotelIsFoodIncluded.get(i)), Short.parseShort(hotelAmountOfRooms.get(i)), Boolean.parseBoolean(hotelIsTVIncluded.get(i)), Boolean.parseBoolean(hotelIsConditionerIncluded.get(i)));
                voucher.addHotel(hotel);
            }
        }
        List<String> costCost = getSubElementTextContent(voucherElement, "Cost", "cost");
        List<String> costIncludes = getSubElementTextContent(voucherElement, "Cost", "includes");
        Cost cost = new Cost(Double.parseDouble(costCost.get(0)), costIncludes);
        voucher.setCost(cost);
        return voucher;
    }

    private static List<String> getElementTextContent(Node voucher, String elementName) {
        NodeList elements = voucher.getChildNodes();
        List<String> content = new ArrayList<>();
        for (int i = 0; i < elements.getLength(); i++) {
            Node cardElem = elements.item(i);
            if (cardElem.getNodeName().equals(elementName)) {
                NodeList childNodes = cardElem.getChildNodes();
                if (childNodes.getLength() == 1) {
                    String text = childNodes.item(0).getTextContent();
                    content.add(text);
                } else {
                    for (int j = 0; j < childNodes.getLength(); j++) {
                        if (childNodes.item(j).getNodeName().equals(elementName)) {
                            String text = childNodes.item(j).getTextContent();
                            content.add(text);
                        }
                    }
                }
            }
        }
        return content;
    }

    private static List<String> getSubElementTextContent(Node voucher, String elementName, String subElementName) {
        NodeList elements = voucher.getChildNodes();
        List<String> content = new ArrayList<>();
        for (int i = 0; i < elements.getLength(); i++) {
            Node cardElem = elements.item(i);
            if (cardElem.getNodeName().equals(elementName)) {
                var childNodes = cardElem.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    if (childNodes.item(j).getNodeName().equals(subElementName)) {
                        String text = childNodes.item(j).getTextContent();
                        content.add(text);
                    }
                }
            }
        }
        return content;
    }

    private static int countElements(Node voucher, String elementName) {
        NodeList elements = voucher.getChildNodes();
        int counter = 0;
        for (int i = 0; i < elements.getLength(); i++) {
            Node cardElem = elements.item(i);
            if (cardElem.getNodeName().equals(elementName)) {
                counter++;
            }
        }
        return counter;
    }
}
