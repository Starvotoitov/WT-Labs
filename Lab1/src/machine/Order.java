package machine;

import machine.coffee.Espresso;
import machine.coffee.CoffeeWithMilk;
import machine.coffee.Americano;
import machine.coffee.Cappuccino;
import machine.coffee.LatteMacchiato;

public class Order {
	private Espresso coffee;
	private int orderID;
	
	public Espresso getCoffee {
		return this.coffee;
	}
	
	public void setCoffee(Espresso newCoffee) {
		this.coffee = newCoffee;
	}
	
	public int getOrderID {
		return this.orderID;
	}
	
	public void setOrderID(int newID) {
		this.orderID = newID;
	}
}