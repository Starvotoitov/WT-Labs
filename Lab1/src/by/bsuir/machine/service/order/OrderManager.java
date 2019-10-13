package by.bsuir.machine.service.order;

import by.bsuir.machine.annotation.FieldInfo;
import by.bsuir.machine.dao.DAO;
import by.bsuir.machine.dao.DataAccessManager;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Класс, объект которого представляет собой список заказов кофемашины.
 * Элементами списка являются объекты класса Order.
 * Предоставляет методы для добавления, удаления элементов, сортировки, сохранения и загрузки списка заказов.
 * Также позволяет выбирать активный заказ и редактировать его содержимое.
 */
public class OrderManager {
    private ArrayList<Order> orderList;
    private DAO<ArrayList<Order>> orderFileManager;
    private Order currentOrder;

    /**
     * Создает объек, устанавливает значение активного профиля равным null, создает объект для сохранения и
     * загрузки данных из файла.
     */
    public OrderManager() {
        currentOrder = null;
        orderFileManager = new DataAccessManager<ArrayList<Order>>();
    }

    /**
     * Добавляет новый заказ в список заказов.
     * @param newOrder Добавляемый заказ.
     */
    public void addNewOrder(Order newOrder) {
        if (newOrder != null) {
            orderList.add(newOrder);
            currentOrder = newOrder;
        }
    }

    /**
     * Позволяет получить заказ из списка заказов по индексу.
     * @param index Индекс запрашиваемого заказа.
     * @return Если индекс корректый - возвращает заказ из списка. В противном случае возвращает null.
     */
    public Order getOrder(int index) {
        if ((index >= 0) && (index < orderList.size())) {
            return orderList.get(index);
        }
        return null;
    }

    /**
     * Удаляет заказ из списка заказов.
     * @param index Индекс удаляемого заказа.
     */
    public void deleteOrder(int index) {
        if ((index >= 0) && (index < orderList.size())) {
            if (orderList.get(index).equals(currentOrder)) {
                currentOrder = null;
            }
            orderList.remove(index);
        }
    }

    /**
     * Изменяет значение выбранного поля у кофе активного заказа на новое значение.
     * @param fieldIndex Индекс поля, значение которого должнло быть изменено.
     * @param newValue Новое значение.
     * @throws IllegalAccessException Если доступ к полю невозможен.
     */
    public void updateOrder(int fieldIndex, int newValue) throws IllegalAccessException {
        if (currentOrder != null) {
            ArrayList<Field> fields = new ArrayList<Field>();
            getAllFields(currentOrder.getCoffee().getClass(), fields);
            fields.get(fieldIndex).setAccessible(true);
            fields.get(fieldIndex).setInt(currentOrder.getCoffee(), newValue);
        }
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

    /**
     * Позволяет выбрать новый активный заказ
     * @param index Индекс заказа в списке заказов, который будет выбран активным.
     */
    public void chooseCurrentOrder(int index) {
        if ((index >= 0) && (index < orderList.size())) {
            currentOrder = orderList.get(index);
        }
    }

    /**
     * Позволяет получить активный заказ.
     * @return Активный заказ. Если активный заказ не выбран - вовращает null.
     */
    public Order getCurrentOrder() {
        return currentOrder;
    }

    /**
     * Позволяет получить количество заказов в списке заказов.
     * @return Количество заказов в списке заказов.
     */
    public int getOrdersCount() {
        return orderList.size();
    }

    /**
     * Сортирует список заказов по правидам, задаваемым выбранным компаратором.
     * @param comparator Компаратор, в соответствии с которым будет проводиться сортировка.
     */
    public void sortOrders(Comparator<Order> comparator) {
        Collections.sort(orderList, comparator);
    }

    /**
     * Сохраняет список заказов в файл в XML-формате.
     * @param path Путь к файлу, в который будет сохранен список. Файл будет создан или, если он уже существует,
     *             будет перезаписан.
     * @throws IOException Если возникла ошибка ввода/вывода.
     */
    public void save(String path) throws IOException {
            orderFileManager.saveToFile(orderList, path);
    }

    /**
     * Загружает списков заказов из файла, сохраненного в XML-формате.
     * @param path Путь к файлу, из которого загружается список.
     */
    public void load(String path) {
        try {
            orderList = orderFileManager.loadToFile(path);
        } catch (IOException | ClassNotFoundException ex) {
            orderList = new ArrayList<Order>();
        }
    }
}
