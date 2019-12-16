package by.bsuir.machine.dao.order.parser.sax;

import by.bsuir.machine.beans.coffee.*;
import by.bsuir.machine.beans.order.Order;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;

public class OrderSAXHandler extends DefaultHandler {
    private ArrayList<Order> orderList = new ArrayList<Order>();
    private Order order;
    private Espresso coffee;
    private Logger logger;

    public OrderSAXHandler() {
        PropertyConfigurator.configure("D://log4j.properties");
        logger = Logger.getLogger(OrderSAXHandler.class);
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) throws SAXException {
        if (qName.equals("order")) {
            order = new Order();
            order.setId(attributes.getValue("id"));
        } else {
            String value = attributes.getValue("value");
            if (value == null) {
                switch (qName) {
                    case "espresso":
                        coffee = new Espresso();
                        break;
                    case "americano":
                        coffee = new Americano();
                        break;
                    case "coffeeWithMilk":
                        coffee = new CoffeeWithMilk();
                        break;
                    case "cappuccino":
                        coffee = new Cappuccino();
                        break;
                    case "latteMacchiato":
                        coffee = new LatteMacchiato();
                        break;
                }
            } else {
                if (qName.equals("date")) {
                    order.setDate(new Date(Long.parseLong(value)));
                } else {
                    setFieldValue(qName, value, coffee.getClass());
                }
            }
        }
    }

    private void setFieldValue(String name, String value, Class currentClass) {
        boolean isFind = false;
        Field[] fields = currentClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals(name)) {
                isFind = true;
                field.setAccessible(true);
                try {
                    field.setInt(coffee, Integer.parseInt(value));
                } catch (IllegalAccessException ex) {
                    logger.error(ex.getMessage());
                }
            }
        }
        if ((!isFind) && (currentClass != Espresso.class)) {
            setFieldValue(name, value, currentClass.getSuperclass());
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "espresso":
            case "americano":
            case "coffeeWithMilk":
            case "cappuccino":
            case "latteMacchiato":
                order.setCoffee(coffee);
                break;
            case "order":
                orderList.add(order);
                order = null;
                break;
        }
    }
}