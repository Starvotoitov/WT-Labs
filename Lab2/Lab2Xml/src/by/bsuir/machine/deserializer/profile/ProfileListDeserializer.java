package by.bsuir.machine.deserializer.profile;

import by.bsuir.machine.beans.profile.Profile;
import by.bsuir.machine.validator.XMLValidator;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import java.io.IOException;
import java.util.ArrayList;

public class ProfileListDeserializer {
    static public ArrayList<Profile> deserialize(String XMLPath, String XSDPath) throws SAXException, IOException {
        if (XMLValidator.validate(XMLPath, XSDPath)) {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            ProfileListSaxHandler handler = new ProfileListSaxHandler();
            reader.setContentHandler(handler);
            reader.parse(new InputSource(XMLPath));

            return handler.getProfileList();
        } else {
            return null;
        }
    }
}
