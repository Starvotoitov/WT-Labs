package machine.order;

import java.io.Serializable;
import machine.coffee.Espresso;
import machine.coffee.CoffeeWithMilk;
import machine.coffee.Americano;
import machine.coffee.Cappuccino;
import machine.coffee.LatteMacchiato;

public class Order implements Serializable {
	private Espresso coffee;
	private int orderID;
	
	public Espresso getCoffee() {
		return this.coffee;
	}
	
	public void setCoffee(Espresso newCoffee) {
		this.coffee = newCoffee;
	}
	
	public int getOrderID() {
		return this.orderID;
	}
	
	public void setOrderID(int newID) {
		this.orderID = newID;
	}
	
	public boolean equals(Object obj) {
		if (null == obj) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Order order = (Order)obj;
		if (!coffee.equals(order.getCoffee()) || orderID != order.getOrderID()) {
			return false;
		}
		return true;
	}
}