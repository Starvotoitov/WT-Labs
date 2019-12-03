package by.bsuir.machine.deserializer.order;

import by.bsuir.machine.beans.order.Order;
import by.bsuir.machine.validator.XMLValidator;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.ArrayList;

public class OrderListDeserializer {
    static public ArrayList<Order> deserialize(String XMLPath, String XSDPath) throws SAXException, IOException {
        if (XMLValidator.validate(XMLPath, XSDPath)) {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            OrderListSaxHandler handler = new OrderListSaxHandler();
            reader.setContentHandler(handler);
            reader.parse(new InputSource(XMLPath));
            return handler.getOrderList();
        } else {
            return null;
        }
    }
}
