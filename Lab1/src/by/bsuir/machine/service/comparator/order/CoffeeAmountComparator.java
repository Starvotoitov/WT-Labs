package by.bsuir.machine.service.comparator.order;

import by.bsuir.machine.annotation.ComparatorInfo;
import by.bsuir.machine.service.order.Order;
import java.util.Comparator;

@ComparatorInfo(name = "По объему кофе",
        description = "Вначале с наименьшим объемом")
public class CoffeeAmountComparator implements Comparator<Order> {
    @Override
    public int compare(Order order1, Order order2) {
        return order1.getCoffee().getCoffeeAmount() - order2.getCoffee().getCoffeeAmount();
    }
}
