package machine.service.comparator.order;

import machine.annotation.ComparatorInfo;
import machine.service.order.Order;
import java.util.Comparator;

@ComparatorInfo(name = "По объему воды",
        description = "Вначале с наименьшим объемом")
public class WaterVolumeComparator implements Comparator<Order> {
    @Override
    public int compare(Order order1, Order order2) {
        return order1.getCoffee().getWaterVolume() - order2.getCoffee().getWaterVolume();
    }
}
