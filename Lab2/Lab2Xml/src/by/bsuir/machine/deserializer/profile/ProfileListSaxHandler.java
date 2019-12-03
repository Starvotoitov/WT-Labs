package by.bsuir.machine.deserializer.profile;

import by.bsuir.machine.beans.coffee.*;
import by.bsuir.machine.beans.profile.Profile;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;

public class ProfileListSaxHandler extends DefaultHandler {
    private ArrayList<Profile> profileList = new ArrayList<Profile>();
    private Profile profile;
    private Espresso coffee;

    public ArrayList<Profile> getProfileList() {
        return profileList;
    }

    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) throws SAXException {
        if (qName.equals("profile")) {
            profile = new Profile();
            profile.setId(attributes.getValue("id"));
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
                switch (qName) {
                    case "creationDate":
                        profile.setCreationDate(new Date(Long.parseLong(value)));
                        break;
                    case "profileName":
                        profile.setProfileName(value);
                        break;
                    default:
                        setFieldValue(qName, value);
                }
            }
        }
    }

    private void setFieldValue(String name, String value) {
        try {
            Field field = coffee.getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.setInt(coffee, Integer.parseInt(value));
        } catch (NoSuchFieldException | IllegalAccessException ex) {
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
                profile.updateSettings(coffee);
                break;
            case "profile":
                profileList.add(profile);
                profile = null;
                break;
        }
    }
}
