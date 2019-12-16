package by.bsuir.machine.service.order;

import by.bsuir.machine.beans.order.Order;

import java.util.ArrayList;

public interface OrderService {
    ArrayList<Order> getNextPage();
    ArrayList<Order> getPreviousPage();
    ArrayList<Order> getCurrentPage();
    void setNewOrderList(String path, int parseType);
    boolean isLastPage();
    boolean isFirstPage();
}
