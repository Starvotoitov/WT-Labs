package machine.management;

import machine.creator.*;
import machine.order.Order;
import machine.order.OrderManager;
import machine.profile.Profile;
import machine.profile.ProfileList;
import machine.coffee.Espresso;
import java.util.ArrayList;

public class DataManager {
    private ArrayList<Creator> creatorsList;
    private OrderManager orderManager;
    private ProfileList profileList;

    public DataManager() {
        orderManager = new OrderManager();
        profileList = new ProfileList();
        creatorsList = new ArrayList<Creator>();
        creatorsList.add(new EspressoCreator());
        creatorsList.add(new AmericanoCreator());
        creatorsList.add(new CappuccinoCreator());
        creatorsList.add(new CoffeeWithMilkCreator());
        creatorsList.add(new LatteMacchiatoCreator());
    }

    public void createOrder(int coffeeIndex) {
        Class<? extends Espresso> coffeeClass = creatorsList.get(coffeeIndex).getCoffeeClass();
        Espresso basicSettings = profileList.getCoffeeSettings(coffeeClass);
        Espresso newCoffee;
        if (basicSettings != null) {
            try {
                newCoffee = (Espresso) basicSettings.clone();
            } catch (CloneNotSupportedException ex) {
                newCoffee = creatorsList.get(coffeeIndex).createCoffee();
                System.out.println(ex.getMessage());
            }
        } else {
            newCoffee = creatorsList.get(coffeeIndex).createCoffee();
        }
        Order newOrder = new Order();
        newOrder.setCoffee(newCoffee);
    }

    public void createProfile() {
        Profile newProfile = new Profile();
        profileList.addProfile(newProfile);
    }

    public Order readOrder(int index) {
        return orderManager.getOrder(index);
    }

    public void updateOrder() {

    }

    public void updateProfile() {

    }

    public void deleteOrder(int index) {
        orderManager.deleteOrder(index);
    }

    public void deleteProfile(int index) {
        profileList.deleteProfile(index);
    }

    public void chooseProfile(int index) {
        profileList.chooseProfile(index);
    }
}
