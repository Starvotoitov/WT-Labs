package by.bsuir.machine.validator;

import org.xml.sax.SAXException;
import javax.xml.validation.SchemaFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XMLValidator {
    static public boolean validate(String XMLPath, String XSDPath) throws SAXException, IOException {
        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        File schemaLocation = new File(XSDPath);
        Schema schema = factory.newSchema(schemaLocation);
        Validator validator = schema.newValidator();
        Source source = new StreamSource(XMLPath);
        boolean result = false;
        try {
            validator.validate(source);
            result = true;
        } catch (SAXException ex) {
            System.out.println("not valid");
            System.out.println(ex.getMessage());
        }
        return result;
    }
}
