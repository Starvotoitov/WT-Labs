package machine.service.order;

import machine.dao.DataAccessManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class OrderManager {
    private ArrayList<Order> orderList;
    private DataAccessManager<ArrayList<Order>> orderFileManager;

    public OrderManager() {
        orderFileManager = new DataAccessManager<ArrayList<Order>>();
        load("");
    }

    public void addNewOrder(Order newOrder) {
        if (newOrder != null) {
            orderList.add(newOrder);
        }
    }

    public Order getOrder(int index) {
        if ((index >= 0) && (index < orderList.size())) {
            return orderList.get(index);
        }
        return null;
    }

    public void deleteOrder(int index) {
        if ((index >= 0) && (index < orderList.size())) {
            orderList.remove(index);
        }
    }

    public void updateOrder(int index, Order newOrder) {
        if ((newOrder != null) && (index >= 0) && (index < orderList.size())) {
            orderList.set(index, newOrder);
        }
    }

    public int getOrdersCount() {
        return orderList.size();
    }

    public void sortOrders(Comparator<Order> comparator) {
        Collections.sort(orderList, comparator);
    }

    public void save(String path) throws IOException {
            orderFileManager.saveToFile(orderList, path);
    }

    public void load(String path) {
        try {
            orderList = orderFileManager.loadToFile(path);
        } catch (IOException | ClassNotFoundException ex) {
            orderList = new ArrayList<Order>();
        }
    }
}
