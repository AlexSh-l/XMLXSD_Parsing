import com.alex.xmlxsdparsing.entity.Cost;
import com.alex.xmlxsdparsing.entity.Hotel;
import com.alex.xmlxsdparsing.entity.TouristVoucher;
import com.alex.xmlxsdparsing.entity.enumvalue.FoodType;
import com.alex.xmlxsdparsing.entity.enumvalue.VoucherType;
import com.alex.xmlxsdparsing.exception.ParserBuildVouchersException;
import com.alex.xmlxsdparsing.exception.ValidationException;
import com.alex.xmlxsdparsing.parser.builder.TouristVouchersBuilder;
import com.alex.xmlxsdparsing.parser.factory.BuilderFactory;
import com.alex.xmlxsdparsing.validator.impl.XMLValidator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.*;

public class XMLParserTest {

    private static final String xmlFileName = "data/tourist-vouchers.xml";
    private static Set<TouristVoucher> expected;

    @BeforeClass
    public static void initializeValidator() {
        XMLValidator validator = new XMLValidator();
        try {
            validator.validateXML();
        } catch (ValidationException e) {
            Assert.fail(e.getMessage());
        }
        expected = new HashSet<>();
        List<String> country = new ArrayList<>();
        country.add("Мексика");
        List<String> transport = new ArrayList<>();
        transport.add("Самолёт");
        transport.add("Автобус");
        List<Hotel> hotels = new ArrayList<>();
        Hotel hotel = new Hotel("BellaVingtol", Short.parseShort("5"), true, FoodType.UAI, Short.parseShort("2"), true, true);
        hotels.add(hotel);
        List<String> costs = new ArrayList<>();
        costs.add("Проживание в отеле");
        costs.add("Поездка от аэропорта");
        Cost cost = new Cost(Double.parseDouble("2000.25"), costs);
        TouristVoucher voucher = new TouristVoucher(VoucherType.PACKET_TOUR, country, Short.parseShort("2"), Short.parseShort("2"), LocalDateTime.parse("2022-07-10T11:00:00"), transport, hotels, cost);
        voucher.setId("ID-1235469875");
        expected.add(voucher);
        country = new ArrayList<>();
        transport = new ArrayList<>();
        hotels = new ArrayList<>();
        costs = new ArrayList<>();
        country.add("Германия");
        transport.add("Автобус");
        hotel = new Hotel("Grettenburg", Short.parseShort("3"), false, Short.parseShort("1"), true, false);
        hotels.add(hotel);
        costs.add("Проживание в отеле");
        costs.add("Поездка на автобусе");
        cost = new Cost(Double.parseDouble("500.0"), costs);
        voucher = new TouristVoucher(VoucherType.SHOP_TOUR, country, Short.parseShort("10"), Short.parseShort("9"), LocalDateTime.parse("2021-12-21T20:30:00"), transport, hotels, cost);
        voucher.setId("ID-1343");
        expected.add(voucher);
        country = new ArrayList<>();
        transport = new ArrayList<>();
        hotels = new ArrayList<>();
        costs = new ArrayList<>();
        country.add("Норвегия");
        country.add("Швеция");
        country.add("Италия");
        transport.add("Автобус");
        transport.add("Паром");
        transport.add("Круизный лайнер");
        hotel = new Hotel("Stratus", Short.parseShort("5"), true, FoodType.UAI, Short.parseShort("5"), true, true);
        hotels.add(hotel);
        hotel = new Hotel("Laint", Short.parseShort("5"), true, FoodType.BB, Short.parseShort("3"), true, false);
        hotels.add(hotel);
        costs.add("Билеты на круизный лайнер");
        costs.add("Поездка на автобусе");
        costs.add("Поездка на пароме");
        costs.add("Проживание в отеле");
        cost = new Cost(Double.parseDouble("5000.0"), costs);
        voucher = new TouristVoucher(VoucherType.CRUISE, country, Short.parseShort("31"), Short.parseShort("32"), LocalDateTime.parse("2022-08-01T13:30:00"), transport, hotels, cost);
        voucher.setId("ID-567");
        expected.add(voucher);
    }

    @Test
    public void domParserTest() {
        TouristVouchersBuilder builder = BuilderFactory.buildParser("dom");
        try {
            builder.buildSetVouchers(xmlFileName);
        } catch (ParserBuildVouchersException e) {
            Assert.fail(e.getMessage());
        }
        Set<TouristVoucher> actual = builder.getVouchers();
        Assert.assertTrue(Arrays.deepEquals(actual.toArray(), expected.toArray()));
    }

    @Test
    public void staxParserTest() {
        TouristVouchersBuilder builder = BuilderFactory.buildParser("stax");
        try {
            builder.buildSetVouchers(xmlFileName);
        } catch (ParserBuildVouchersException e) {
            Assert.fail(e.getMessage());
        }
        Set<TouristVoucher> actual = builder.getVouchers();
        Assert.assertTrue(Arrays.deepEquals(actual.toArray(), expected.toArray()));
    }

    @Test
    public void saxParserTest() {
        TouristVouchersBuilder builder = BuilderFactory.buildParser("sax");
        try {
            builder.buildSetVouchers(xmlFileName);
        } catch (ParserBuildVouchersException e) {
            Assert.fail(e.getMessage());
        }
        Set<TouristVoucher> actual = builder.getVouchers();
        Assert.assertTrue(Arrays.deepEquals(actual.toArray(), expected.toArray()));
    }
}
