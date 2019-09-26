package machine.order;

import java.util.ArrayList;
import machine.order.Order;

public class OrderManager {
	private ArrayList<Order> orderList;
	
	public void addNewOrder(Order newOrder) {
		if (newOrder != null) {
			this.orderList.add(newOrder);
		}
	}
}