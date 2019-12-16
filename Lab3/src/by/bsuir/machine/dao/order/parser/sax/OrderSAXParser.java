package by.bsuir.machine.dao.order.parser.sax;

import by.bsuir.machine.beans.order.Order;
import by.bsuir.machine.dao.order.parser.OrderParser;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class OrderSAXParser implements OrderParser {
    private Logger logger;

    public OrderSAXParser() {
        PropertyConfigurator.configure("D://log4j.properties");
        logger = Logger.getLogger(OrderSAXParser.class);
    }

    @Override
    public ArrayList<Order> parse(InputStream input) {
        ArrayList<Order> result;
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            OrderSAXHandler handler = new OrderSAXHandler();
            reader.setContentHandler(handler);
            reader.parse(new InputSource(input));
            result = handler.getOrderList();
        } catch (SAXException | IOException ex) {
            result = null;
            logger.error(ex.getMessage());
        }
        return result;
    }
}
