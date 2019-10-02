package machine.order;

import java.util.ArrayList;

public class OrderManager {
    private ArrayList<Order> orderList;

    public OrderManager() {
        orderList = new ArrayList<Order>();
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
        if ((index >= 0) && (index > orderList.size())) {
            orderList.remove(index);
        }
    }

    public void updateOrder(int index, Order newOrder) {
        if ((newOrder != null) && (index >= 0) && (index < orderList.size())) {
//            orderList.remove(index);
//            orderList.add(index, newOrder);
            orderList.set(index, newOrder);
        }
    }
}
