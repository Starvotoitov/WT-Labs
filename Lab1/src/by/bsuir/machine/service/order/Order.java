package by.bsuir.machine.service.order;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import by.bsuir.machine.service.coffee.Espresso;

/**
 * Хранит информацию о заказе, включающую в себя время создания заказа, а также информацию о заказанном кофе.
 */
public class Order implements Serializable {
    private Espresso coffee;
    private Date date;

    /**
     * Создает объект, в качестве значения кофе устанавливается null, инициализируется время создания объекта.
     */
    public Order() {
        coffee = null;
        date = new Date();
    }

    /**
     * Создает объект, в качестве значения кофе устанавливается выбранное значение ,инициализируется
     * время создания объекта.
     * @param newCoffee Значение кофе.
     */
    public Order(Espresso newCoffee) {
        coffee = newCoffee;
        date = new Date();
    }

    /**
     * Позволяет получить информацию о заказанном кофе.
     * @return Информация о заказанный кофе.
     */
    public Espresso getCoffee() {
        return this.coffee;
    }

    /**
     * Позволяет установить новое значение для заказанного кофе.
     * @param newCoffee Новое значение кофе.
     */
    public void setCoffee(Espresso newCoffee) {
        if (newCoffee != null) {
            this.coffee = newCoffee;
        }
    }

    /**
     * Позволяет получить время создания заказа.
     * @return Время создания заказа.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Позволяет установить новое значение времени создания заказа.
     * @param newDate Новое время.
     */
    public void setDate(Date newDate) {
        date = newDate;
    }

    /**
     * Сравнивает заказ с объектом.
     * @param obj Объект для сравнения.
     * @return true - если равны, иначе false.
     */
    @Override
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
        if (!coffee.equals(order.getCoffee())) {
            return false;
        }
        return true;
    }

    /**
     * Вычисляет значение хэшкода заказа.
     * @return Значение хэшкода заказа.
     */
    @Override
    public int hashCode() {
        return Objects.hash(coffee, date);
    }
}
