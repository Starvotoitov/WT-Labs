package machine.service;

import machine.annotation.FieldInfo;
import machine.service.comparator.order.CoffeeAmountComparator;
import machine.service.comparator.order.OrderDateComparator;
import machine.service.comparator.order.TemperatureComparator;
import machine.service.comparator.order.WaterVolumeComparator;
import machine.service.comparator.profile.CreationDateComparator;
import machine.service.comparator.profile.ProfileNameComparator;
import machine.service.creator.*;
import machine.service.order.Order;
import machine.service.order.OrderManager;
import machine.service.profile.Profile;
import machine.service.profile.ProfileList;
import machine.service.coffee.Espresso;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;

public class DataManager {
    private final static String PROFILE_FILE_PATH = "profiles.xml";
    private final static String ORDER_FILE_PATH = "orders.xml";

    private ArrayList<Creator> creatorsList;
    private ArrayList<Comparator<Order>> orderComparatorsList;
    private ArrayList<Comparator<Profile>> profileComparatorsList;
    private OrderManager orderManager;
    private ProfileList profileList;

    public DataManager() {
        orderManager = new OrderManager();
        profileList = new ProfileList();
        creatorsList = new ArrayList<Creator>();
        orderComparatorsList = new ArrayList<Comparator<Order>>();
        profileComparatorsList = new ArrayList<Comparator<Profile>>();

        creatorsList.add(new EspressoCreator());
        creatorsList.add(new AmericanoCreator());
        creatorsList.add(new CappuccinoCreator());
        creatorsList.add(new CoffeeWithMilkCreator());
        creatorsList.add(new LatteMacchiatoCreator());

        orderComparatorsList.add(new OrderDateComparator());
        orderComparatorsList.add(new CoffeeAmountComparator());
        orderComparatorsList.add(new TemperatureComparator());
        orderComparatorsList.add(new WaterVolumeComparator());

        profileComparatorsList.add(new CreationDateComparator());
        profileComparatorsList.add(new ProfileNameComparator());
    }

    public Order createOrder(int coffeeIndex) {
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
        return newOrder;
    }

    public void addOrder(Order newOrder) {
        if (newOrder != null) {
            orderManager.addNewOrder(newOrder);
        }
    }

    public void createProfile(String name) {
        Profile newProfile = new Profile();
        newProfile.setProfileName(name);
        profileList.addProfile(newProfile);
    }

    public Order readOrder(int index) {
        return orderManager.getOrder(index);
    }

    public void updateOrder(Order order, int fieldIndex, int newValue) throws IllegalAccessException {
        ArrayList<Field> fields = new ArrayList<Field>();
        getAllFields(order.getCoffee().getClass(), fields);
        fields.get(fieldIndex - 1).setAccessible(true);
        fields.get(fieldIndex - 1).setInt(order.getCoffee(), newValue);
    }

    public void updateProfile(Espresso coffee) {
        profileList.updateSettings(coffee);
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

    public boolean isCurrentProfileSet() {
        return profileList.isCurrentProfileSet();
    }

    public String getCurrentProfile() {
        return profileList.getProfileName();
    }

    public Profile[] getProfiles() {
        Profile[] profiles = new Profile[profileList.getProfileCount()];
        for (int i = 0; i < profiles.length; i++) {
            profiles[i] = profileList.getProfile(i);
        }
        return profiles;
    }

    public int getProfileCount() {
        return profileList.getProfileCount();
    }

    public Class<? extends Espresso>[] getCoffeeList() {
        Class<? extends Espresso>[] coffeeList = new Class[creatorsList.size()];
        for (int i = 0; i < creatorsList.size(); i++) {
            coffeeList[i] = creatorsList.get(i).getCoffeeClass();
        }
        return coffeeList;
    }

    public Order[] getOrders() {
        Order[] orders = new Order[orderManager.getOrdersCount()];
        for (int i = 0; i < orderManager.getOrdersCount(); i++) {
            orders[i] = orderManager.getOrder(i);
        }
        return orders;
    }

    public int getOrdersCount() {
        return orderManager.getOrdersCount();
    }

    public ArrayList<Field> getCoffeeInfo(Espresso coffee) {
        ArrayList<Field> fieldList = new ArrayList<Field>();
        getAllFields(coffee.getClass(), fieldList);
        return fieldList;
    }

    private void getAllFields(Class<?> coffee, ArrayList<Field> fields) {
        for (Field field : coffee.getDeclaredFields()) {
            if (field.isAnnotationPresent(FieldInfo.class)) {
                if (field.getAnnotation(FieldInfo.class).isRepresented()) {
                    fields.add(field);
                }
            }
        }

        if (coffee.getSuperclass() != null) {
            getAllFields(coffee.getSuperclass(), fields);
        }
    }

    public void sortOrders(int comparatorIndex, boolean isReversed) {
        if ((comparatorIndex >= 0) && (comparatorIndex < orderComparatorsList.size())) {
            if (isReversed) {
                orderManager.sortOrders(orderComparatorsList.get(comparatorIndex).reversed());
            } else {
                orderManager.sortOrders(orderComparatorsList.get(comparatorIndex));
            }
        }
    }

    public void sortProfiles(int comparatorIndex, boolean isReversed) {
        if ((comparatorIndex >= 0) && (comparatorIndex < profileComparatorsList.size())) {
            if (isReversed) {
                profileList.sortProfiles(profileComparatorsList.get(comparatorIndex).reversed());
            } else {
                profileList.sortProfiles(profileComparatorsList.get(comparatorIndex));
            }
        }
    }

    public Class<Comparator<Order>>[] getOrderComparators() {
        Class<Comparator<Order>>[] comparators = new Class[orderComparatorsList.size()];
        for (int i = 0; i < orderComparatorsList.size(); i++) {
            comparators[i] = (Class<Comparator<Order>>)orderComparatorsList.get(i).getClass();
        }
        return comparators;
    }

    public Class<Comparator<Profile>>[] getProfileComparators() {
        Class<Comparator<Profile>>[] comparators = new Class[profileComparatorsList.size()];
        for (int i = 0; i < profileComparatorsList.size(); i++) {
            comparators[i] = (Class<Comparator<Profile>>)profileComparatorsList.get(i).getClass();
        }
        return comparators;
    }

    public void saveProfiles() throws IOException {
        profileList.save(PROFILE_FILE_PATH);
    }

    public void saveOrders() throws IOException {
        orderManager.save(ORDER_FILE_PATH);
    }
}
