package by.bsuir.machine.service;

import by.bsuir.machine.service.comparator.order.CoffeeAmountComparator;
import by.bsuir.machine.service.comparator.order.OrderDateComparator;
import by.bsuir.machine.service.comparator.order.TemperatureComparator;
import by.bsuir.machine.service.comparator.order.WaterVolumeComparator;
import by.bsuir.machine.service.comparator.profile.CreationDateComparator;
import by.bsuir.machine.service.comparator.profile.ProfileNameComparator;
import by.bsuir.machine.service.creator.*;
import by.bsuir.machine.service.order.Order;
import by.bsuir.machine.service.order.OrderManager;
import by.bsuir.machine.service.profile.Profile;
import by.bsuir.machine.service.profile.ProfileList;
import by.bsuir.machine.service.coffee.Espresso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Реализует бизнес-логику кофемашины. Содержит методы для работы со списком профилей и списком заказов.
 * Позволяет добавлять, удалять, изменять, выбирать, сортировать списки, а также получать информацию заказах и профилях.
 */
public class DataManager {
    private final static String PROFILE_FILE_PATH = "profiles.xml";
    private final static String ORDER_FILE_PATH = "orders.xml";

    private ArrayList<Creator> creatorsList;
    private ArrayList<Comparator<Order>> orderComparatorsList;
    private ArrayList<Comparator<Profile>> profileComparatorsList;
    private OrderManager orderManager;
    private ProfileList profileList;

    /**
     * Создает объект и инициализирует списки доступных для создания кофе, списки компараторов для заказов и профилей,
     * создает и загружает из файлов profile.xml и orders.xml списки профилей и заказов.
     */
    public DataManager() {
        orderManager = new OrderManager();
        orderManager.load(ORDER_FILE_PATH);
        profileList = new ProfileList();
        profileList.load(PROFILE_FILE_PATH);
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

    /**
     * Создает и добавляет в список новый заказ. Если выбран профиль, в котором сохранены настройки для создаваемого
     * типа кофе, то начальные настройки для кофе берутся из профиля. В противном случае создается кофе с стандартными
     * настройками.
     * @param coffeeIndex Номер создаваемого кофе в списке кофе, доступных для создания.
     */
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
        orderManager.addNewOrder(newOrder);
    }

    /**
     * Создает новый профиль с указанным именем и добавляет его в список профилей.
     * @param name Имя нового профиля.
     */
    public void createProfile(String name) {
        Profile newProfile = new Profile();
        newProfile.setProfileName(name);
        profileList.addProfile(newProfile);
    }

    /**
     * Изменяет значение одного из полей кофе активного заказа на новое значение.
     * @param fieldIndex Индекс изменяемого поля.
     * @param newValue Новое значение поля.
     * @throws IllegalAccessException Если доступ к полю невозможен
     */
    public void updateOrder(int fieldIndex, int newValue) throws IllegalAccessException {
        orderManager.updateOrder(fieldIndex, newValue);
    }

    /**
     * Сохраняет настройки кофе активного заказа в активном профиле, если они выбраны.
     */
    public void updateProfile() {
        Espresso coffee = orderManager.getCurrentOrder().getCoffee();
        if (coffee != null) {
            profileList.updateSettings(coffee);
        }
    }

    /**
     * Удаляет заказ из списка заказов.
     * @param index Индекс удаляемого заказа
     */
    public void deleteOrder(int index) {
        orderManager.deleteOrder(index);
    }

    /**
     * Удаляет профиль из списка профилей.
     * @param index Индекс удаляемого профиля.
     */
    public void deleteProfile(int index) {
        profileList.deleteProfile(index);
    }

    /**
     * Выбирает новый активный профиль.
     * @param index Индекс профиля, который будет выбран активным.
     */
    public void chooseProfile(int index) {
        profileList.chooseProfile(index);
    }

    /**
     * Выбирает новый активный заказ.
     * @param index Индекс заказа, который будет выбран активным.
     */
    public void chooseCurrentOrder(int index) {
        orderManager.chooseCurrentOrder(index);
    }

    /**
     * Возвращает значение имени активного профиля.
     * @return Имя активного профиля. Если активный профиль не выбран возвращает null.
     */
    public String getCurrentProfile() {
        return profileList.getProfileName();
    }

    /**
     * Возращает активный заказ.
     * @return Активный заказ. Если активный заказ не выбран возвращает null.
     */
    public Order getCurrentOrder() {
        return orderManager.getCurrentOrder();
    }

    /**
     * Возвращает массив, состоящий из всех профилей из списка профилей.
     * @return Массив профилей из списка профилей.
     */
    public Profile[] getProfiles() {
        Profile[] profiles = new Profile[profileList.getProfileCount()];
        for (int i = 0; i < profiles.length; i++) {
            profiles[i] = profileList.getProfile(i);
        }
        return profiles;
    }

    /**
     * Возвращает список всех доступных для создание кофе.
     * @return Массив доступных для создания кофе.
     */
    public Class<? extends Espresso>[] getCoffeeList() {
        Class<? extends Espresso>[] coffeeList = new Class[creatorsList.size()];
        for (int i = 0; i < creatorsList.size(); i++) {
            coffeeList[i] = creatorsList.get(i).getCoffeeClass();
        }
        return coffeeList;
    }

    /**
     * Возвращает массив всех заказов из списка заказов.
     * @return Массив заказов из списка заказов.
     */
    public Order[] getOrders() {
        Order[] orders = new Order[orderManager.getOrdersCount()];
        for (int i = 0; i < orderManager.getOrdersCount(); i++) {
            orders[i] = orderManager.getOrder(i);
        }
        return orders;
    }

    /**
     * Возвращает число заказов в списке заказов.
     * @return Число заказов в списке заказов.
     */
    public int getOrdersCount() {
        return orderManager.getOrdersCount();
    }

    /**
     * Сортирует список заказов в соответствии с выбранными правилами сортировки. При необходимости выполняется
     * обратная сортировка.
     * @param comparatorIndex Индекс компаратора, задающего правила сотрировки, в массиве доступных компараторов.
     * @param isReversed Если значение true - выполняется обратная сортировка, в противном случае выполняется
     *                   прямая сортировка.
     */
    public void sortOrders(int comparatorIndex, boolean isReversed) {
        if ((comparatorIndex >= 0) && (comparatorIndex < orderComparatorsList.size())) {
            if (isReversed) {
                orderManager.sortOrders(orderComparatorsList.get(comparatorIndex).reversed());
            } else {
                orderManager.sortOrders(orderComparatorsList.get(comparatorIndex));
            }
        }
    }

    /**
     * Сортирует список профилей в соответствии с выбранными правилами сортировки. При необходимости выполняется
     * обратная сортировка.
     * @param comparatorIndex Индекс компаратора, задающего правила сотрировки, в массиве доступных компараторов.
     * @param isReversed Если значение true - выполняется обратная сортировка, в противном случае выполняется
     *                   прямая сортировка.
     */
    public void sortProfiles(int comparatorIndex, boolean isReversed) {
        if ((comparatorIndex >= 0) && (comparatorIndex < profileComparatorsList.size())) {
            if (isReversed) {
                profileList.sortProfiles(profileComparatorsList.get(comparatorIndex).reversed());
            } else {
                profileList.sortProfiles(profileComparatorsList.get(comparatorIndex));
            }
        }
    }

    /**
     * Возвращает массив всех компараторов, доступных для использования при сортировке списка заказов.
     * @return Массив компараторов.
     */
    public Class<Comparator<Order>>[] getOrderComparators() {
        Class<Comparator<Order>>[] comparators = new Class[orderComparatorsList.size()];
        for (int i = 0; i < orderComparatorsList.size(); i++) {
            comparators[i] = (Class<Comparator<Order>>)orderComparatorsList.get(i).getClass();
        }
        return comparators;
    }

    /**
     * Возвращает массив всех компараторов, доступных для использования при сортировке списка профилей.
     * @return Массив компараторов.
     */
    public Class<Comparator<Profile>>[] getProfileComparators() {
        Class<Comparator<Profile>>[] comparators = new Class[profileComparatorsList.size()];
        for (int i = 0; i < profileComparatorsList.size(); i++) {
            comparators[i] = (Class<Comparator<Profile>>)profileComparatorsList.get(i).getClass();
        }
        return comparators;
    }

    /**
     * Сохраняет списков профилей в XML-формате в файле profiles.xml.
     * @throws IOException Если возникла ошибка ввода/вывода
     */
    public void saveProfiles() throws IOException {
        profileList.save(PROFILE_FILE_PATH);
    }

    /**
     * Сохраняет список заказов в XML-формате в файле orders.xml.
     * @throws IOException Если возникла ошибка ввода/вывода
     */
    public void saveOrders() throws IOException {
        orderManager.save(ORDER_FILE_PATH);
    }
}
