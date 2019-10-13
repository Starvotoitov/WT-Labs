package by.bsuir.machine.service.comparator.order;

import by.bsuir.machine.annotation.ComparatorInfo;
import by.bsuir.machine.service.order.Order;
import java.util.Comparator;

@ComparatorInfo(name = "По времени заказа",
        description = "Вначале с самой ранней датой")
public class OrderDateComparator implements Comparator<Order> {
    @Override
    public int compare(Order order1, Order order2) {
        return order1.getDate().compareTo(order2.getDate());
    }
}
