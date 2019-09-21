package machine;

import machine.Order;

public class OrderManager {
	private ArrayList<Order> orderList;
	
	public void addNewOrder(Order newOrder) {
		this.orderList.add(newOrder);
	}
}