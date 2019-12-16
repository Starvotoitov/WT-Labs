package by.bsuir.machine.dao.order.parser.stax;

import by.bsuir.machine.beans.coffee.*;
import by.bsuir.machine.beans.order.Order;
import by.bsuir.machine.dao.order.parser.OrderParser;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;

public class OrderStAXParser implements OrderParser {
    private Logger logger;

    public OrderStAXParser() {
        PropertyConfigurator.configure("D://log4j.properties");
        logger = Logger.getLogger(OrderStAXParser.class);
    }

    @Override
    public ArrayList<Order> parse(InputStream input) {
        ArrayList<Order> result;
        try {
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
            result = processParsing(reader);
        } catch (XMLStreamException ex) {
            result = null;
            logger.error(ex.getMessage());
        }
        return result;
    }

    private ArrayList<Order> processParsing(XMLStreamReader reader) throws XMLStreamException {
        ArrayList<Order> result = new ArrayList<Order>();
        Order order = null;
        Espresso coffee = null;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT: {
                    String element = reader.getLocalName();
                    switch (element) {
                        case "order":
                            order = new Order();
                            order.setId(reader.getAttributeValue(null, "id"));
                            break;
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
                        case "date":
                            order.setDate(new Date(Long.parseLong(reader.getAttributeValue(null, "value"))));
                            break;
                        case "temperature":
                        case "coffeeAmount":
                        case "waterVolume":
                        case "dilutionWaterVolume":
                        case "milkVolume":
                        case "milkFoamVolume":
                        case "whippedCreamVolume":
                            setFieldValue(element, reader.getAttributeValue(null, "value"), coffee, coffee.getClass());
                    }
                    break;
                }
                case XMLStreamConstants.END_ELEMENT: {
                    String element = reader.getLocalName();
                    switch (element) {
                        case "espresso":
                        case "americano":
                        case "coffeeWithMilk":
                        case "cappuccino":
                        case "latteMacchiato":
                            order.setCoffee(coffee);
                            break;
                        case "order":
                            result.add(order);
                            order = null;
                            break;
                    }
                    break;
                }
            }
        }
        return result;
    }

    private void setFieldValue(String name, String value, Espresso coffee, Class currentClass) {
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
            setFieldValue(name, value, coffee, currentClass.getSuperclass());
        }
    }
}
