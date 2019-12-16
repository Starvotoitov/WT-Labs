package by.bsuir.machine.dao.order.parser;

import by.bsuir.machine.beans.order.Order;
import org.xml.sax.SAXException;

import java.io.InputStream;
import java.util.ArrayList;

public interface OrderParser {
    ArrayList<Order> parse(InputStream input);
}
