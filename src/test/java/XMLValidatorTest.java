import com.alex.xmlxsdparsing.exception.ValidationException;
import com.alex.xmlxsdparsing.validator.impl.XMLValidator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class XMLValidatorTest {

    private static XMLValidator validator;

    @BeforeClass
    public static void initializeValidator() {
        validator = new XMLValidator();
    }

    @Test
    public void validateXMLTest() {
        boolean actual = false;
        try {
            actual = validator.validateXML();
        } catch (ValidationException e) {
            Assert.fail(e.getMessage());
        }
        Assert.assertTrue(actual);
    }
}
