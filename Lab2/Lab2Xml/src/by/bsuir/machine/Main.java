package by.bsuir.machine;

import by.bsuir.machine.dao.DataBaseManager;
import by.bsuir.machine.deserializer.order.OrderListDeserializer;
import by.bsuir.machine.deserializer.profile.ProfileListDeserializer;
import by.bsuir.machine.beans.coffee.Espresso;
import by.bsuir.machine.beans.order.Order;
import by.bsuir.machine.beans.profile.Profile;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            ArrayList<Order> list = OrderListDeserializer.deserialize("OrderList.xml", "OrderList.xsd");
            ArrayList<Profile> profList = ProfileListDeserializer.deserialize("ProfileList.xml", "ProfileList.xsd");
            DataBaseManager dataBaseManager = new DataBaseManager();
            dataBaseManager.createConnection();
            dataBaseManager.insertOrders(list);
            dataBaseManager.insertProfiles(profList);
            dataBaseManager.closeConnection();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
