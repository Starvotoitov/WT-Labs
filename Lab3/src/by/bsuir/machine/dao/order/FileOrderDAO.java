package by.bsuir.machine.dao.order;

import by.bsuir.machine.beans.order.Order;
import by.bsuir.machine.dao.order.parser.OrderParser;
import by.bsuir.machine.dao.order.parser.dom.OrderDOMParser;
import by.bsuir.machine.dao.order.parser.sax.OrderSAXParser;
import by.bsuir.machine.dao.order.parser.stax.OrderStAXParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class FileOrderDAO implements OrderDAO {
    private ArrayList<OrderParser> parsers;

    public FileOrderDAO() {
        parsers = new ArrayList<OrderParser>();
        parsers.add(new OrderSAXParser());
        parsers.add(new OrderStAXParser());
        parsers.add(new OrderDOMParser());
    }

    @Override
    public ArrayList<Order> getOrderList(String path, int parseType) {
        ArrayList<Order> orderList;

        try {
            InputStream inputStream = new FileInputStream(path);
            orderList = parsers.get(parseType).parse(inputStream);
        } catch (FileNotFoundException ex) {
            orderList = null;
        }

        return orderList;
    }
}
