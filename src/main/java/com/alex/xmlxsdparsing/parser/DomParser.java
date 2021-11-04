package com.alex.xmlxsdparsing.parser;

import com.alex.xmlxsdparsing.entity.TouristVoucher;
import com.alex.xmlxsdparsing.entity.enumerationvalue.VoucherType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

public class DomParser {

    private Set<TouristVoucher> vouchers;
    private DocumentBuilder docBuilder;
    private static final Logger logger = LogManager.getLogger();

    public DomParser() {
        vouchers = new HashSet<TouristVoucher>();
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
            //Element root = doc.getDocumentElement();
            Node root = doc.getDocumentElement();
            NodeList touristVouchers = root.getChildNodes();

            // getting a list of <student> child elements
            //NodeList vouchersList = root.getElementsByTagName("c:tourist-voucher");

            //var x = root.getAttributes();
            //NamedNodeMap vouchersMap = root.getAttributes();
            for (int i = 0; i < touristVouchers.getLength(); i++) {
                //var e = vouchersMap.item(1);
                //Node voucherElement = vouchersMap.item(i);
                var voucherNode = touristVouchers.item(i);
                //var rr = r.item(0);

                if (voucherNode.getNodeName().equals("c:tourist-voucher")) {
                    //var str = r.getTextContent();
                    //Node voucherElement = touristVouchers.item(i);
                    TouristVoucher voucher = buildVoucher(voucherNode);
                    vouchers.add(voucher);
                }
                /*Element voucherElement = (Element) touristVouchers.item(i);
                TouristVoucher voucher = buildVoucher(voucherElement);
                vouchers.add(voucher);*/

            }
        } catch (IOException | SAXException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private TouristVoucher buildVoucher(Node voucherElement) {
        TouristVoucher voucher = new TouristVoucher();
        // add null check
        var k = voucherElement.getAttributes().getNamedItem("id");
        //var st = getElementTextContent(voucherElement, "Country");
        //st.item(0)
        //voucher.setId(voucherElement.getAttribute("id"));
        //voucher.setName(voucherElement.getAttribute("name"));
        //voucher.setVoucherType(voucherElement.getAttribute("Voucher-type"));
        var t = getElementTextContent(voucherElement, "Country");
        var kt = getElementTextContent(voucherElement, "Cost");
        var rkt = getElementTextContent(voucherElement, "Transport");
        //var r = voucherElement.getElementsByTagName("Country");

        //voucher.setCountry(voucherElement.getAttributes().getNamedItem("Country"));

        //student.setFaculty(studentElement.getAttribute("faculty"));
        /*student.setName(getElementTextContent(studentElement, "name"));
        Integer tel = Integer.parseInt(getElementTextContent(studentElement, "telephone"));
        student.setTelephone(tel);
        Student.Address address = student.getAddress();
        // init an address object
        Element adressElement =
                (Element) studentElement.getElementsByTagName("address").item(0);
        address.setCountry(getElementTextContent(adressElement, "country"));
        address.setCity(getElementTextContent(adressElement, "city"));
        address.setStreet(getElementTextContent(adressElement, "street"));
        student.setLogin(studentElement.getAttribute("login"));*/
        return voucher;
    }

    // get the text content of the tag
    private static List<String> getElementTextContent(Node voucher, String elementName) {
        NodeList elements = voucher.getChildNodes();
        List<String> content = new ArrayList<>();
        for (int i = 0; i < elements.getLength(); i++) {
            Node cardElem = elements.item(i);
            if (cardElem.getNodeName().equals(elementName)) {
                //var e = cardElem.getChildNodes().getLength();
                if (cardElem.getChildNodes().getLength() == 1) {
                    String text = cardElem.getChildNodes().item(0).getTextContent();
                    content.add(text);
                } else {
                    for (int j = 0; j < cardElem.getChildNodes().getLength(); j++) {
                        if(cardElem.getChildNodes().item(j).getNodeType() != Node.TEXT_NODE) {
                            String text = cardElem.getChildNodes().item(j).getTextContent();
                            content.add(text);
                        }
                    }
                }
            }
        }
        return content;
    }
}
