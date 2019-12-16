package by.bsuir.machine.dao.order;

import by.bsuir.machine.beans.order.Order;

import java.util.ArrayList;

public interface OrderDAO {
    ArrayList<Order> getOrderList(String path, int parseType);
}
