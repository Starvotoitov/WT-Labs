package by.bsuir.machine.dao.order.parser.dom;

import by.bsuir.machine.beans.coffee.*;
import by.bsuir.machine.beans.order.Order;
import by.bsuir.machine.dao.order.parser.OrderParser;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;

public class OrderDOMParser implements OrderParser {
    private Logger logger;

    public OrderDOMParser() {
        PropertyConfigurator.configure("D://log4j.properties");
        logger = Logger.getLogger(OrderDOMParser.class);
    }

    @Override
    public ArrayList<Order> parse(InputStream input) {
        ArrayList<Order> resultList;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(input);

            resultList = new ArrayList<Order>();
            NodeList orderElements = document.getDocumentElement().getElementsByTagName("order");
            for (int i = 0; i < orderElements.getLength(); i++) {
                Order order = new Order();
                Espresso coffee = null;

                NamedNodeMap attributes = orderElements.item(i).getAttributes();
                order.setId(attributes.getNamedItem("id").getNodeValue());

                NodeList orderParams = orderElements.item(i).getChildNodes();

                attributes = orderParams.item(1).getAttributes();
                order.setDate(new Date(Long.parseLong(attributes.getNamedItem("value").getNodeValue())));

                for (int k = 0; k < orderParams.getLength(); k++) {
                    String a = orderParams.item(k).getNodeName();
                    String b = "a";
                }

                switch (orderParams.item(3).getNodeName()) {
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

                NodeList coffeeParams = orderParams.item(3).getChildNodes();

                for (int j = 0; j < coffeeParams.getLength(); j++) {
                    if (coffeeParams.item(j).getNodeType() == Node.ELEMENT_NODE) {
                        attributes = coffeeParams.item(j).getAttributes();
                        setFieldValue(coffeeParams.item(j).getNodeName(), attributes.getNamedItem("value").getNodeValue(), coffee, coffee.getClass());
                    }
                }
                order.setCoffee(coffee);
                resultList.add(order);
            }
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            logger.error(ex.getMessage());
            resultList = null;
        }
        return resultList;
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
