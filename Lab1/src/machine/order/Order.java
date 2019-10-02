package machine.order;

import java.io.Serializable;
import java.util.Date;
import machine.coffee.Espresso;

public class Order implements Serializable {
    private Espresso coffee;
    private Date date;
    private int orderID;

    public Espresso getCoffee() {
        return this.coffee;
    }

    public void setCoffee(Espresso newCoffee) {
        if (newCoffee != null) {
            this.coffee = newCoffee;
        }
    }

    public int getOrderID() {
        return this.orderID;
    }

    public void setOrderID(int newID) {
        this.orderID = newID;
    }

    public Date getDate() {
        return date;
    }

    public Order() {
        orderID = 0;
        coffee = null;
        date = new Date();
    }

    public Order(int newID, Espresso newCoffee) {
        orderID = newID;
        coffee = newCoffee;
        date = new Date();
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
