package by.bsuir.machine.view;

import by.bsuir.machine.annotation.CoffeeInfo;
import by.bsuir.machine.annotation.ComparatorInfo;
import by.bsuir.machine.annotation.FieldInfo;
import by.bsuir.machine.service.coffee.Espresso;
import by.bsuir.machine.service.order.Order;
import by.bsuir.machine.service.profile.Profile;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;

public class ViewManager {
    public void showOrder(Order order) {
        if (order != null) {
            ArrayList<Field> fields = getCoffeeInfo(order.getCoffee());
            System.out.println(order.getDate());
            System.out.println(getCoffeeName(order.getCoffee().getClass()));
            showCoffeeInfo(fields, order.getCoffee());
        } else {
            System.out.println("Заказ не выбран");
        }
    }

    public void showOrderList(Order[] orderList) {
        for (int i = 0; i < orderList.length; i++) {
            System.out.printf("----------%d----------\n", i + 1);
            showOrder(orderList[i]);
            System.out.println("---------------------");
        }
    }

    public void showCoffeeList(Class<? extends Espresso>[] coffeeList) {
        for (int i = 0; i < coffeeList.length; i++) {
            System.out.printf("%d: %s\n", i + 1, getCoffeeName(coffeeList[i]));
        }
    }

    private void showCoffeeInfo(ArrayList<Field> coffeeInfo, Espresso coffee) {
        for (Field field : coffeeInfo) {
            try {
                field.setAccessible(true);
                System.out.println(field.getAnnotation(FieldInfo.class).name() + ": " + field.get(coffee));
            } catch (IllegalAccessException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private String getCoffeeName(Class<? extends Espresso> coffee) {
        if (coffee.isAnnotationPresent(CoffeeInfo.class)) {
            return coffee.getAnnotation(CoffeeInfo.class).name();
        } else {
            return coffee.getName();
        }
    }

    public void showCurrentProfile(String name) {
        if (name != null) {
            System.out.println("Текущий профиль: " + name);
        } else {
            System.out.println("Текущий профиль: не выбран");
        }
    }

    public void showProfileList(Profile[] profiles) {
        for (int i = 0; i < profiles.length; i++) {
            System.out.printf("----------%d----------\n", i + 1);
            System.out.println(profiles[i].getCreationDate());
            System.out.println(profiles[i].getProfileName());
            System.out.println("---------------------");
        }
    }

    public void showOrderSorts(Class<Comparator<Order>>[] comparators) {
        for (int i = 0; i < comparators.length; i++) {
            System.out.printf("----------%d----------\n", i + 1);
            if (comparators[i].isAnnotationPresent(ComparatorInfo.class)) {
                System.out.println(comparators[i].getAnnotation(ComparatorInfo.class).name());
                System.out.println(comparators[i].getAnnotation(ComparatorInfo.class).description());
            } else {
                System.out.println(comparators[i].getName());
            }
            System.out.println("---------------------");
        }
    }

    public void showProfileSorts(Class<Comparator<Profile>>[] comparators) {
        for (int i = 0; i < comparators.length; i++) {
            System.out.printf("----------%d----------\n", i + 1);
            if (comparators[i].isAnnotationPresent(ComparatorInfo.class)) {
                System.out.println(comparators[i].getAnnotation(ComparatorInfo.class).name());
                System.out.println(comparators[i].getAnnotation(ComparatorInfo.class).description());
            } else {
                System.out.println(comparators[i].getName());
            }
            System.out.println("---------------------");
        }
    }

    public void showErrorMessage(String message) {
        System.out.println("ERROR");
        System.out.println(message);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    private ArrayList<Field> getCoffeeInfo(Espresso coffee) {
        ArrayList<Field> fieldList = new ArrayList<Field>();
        getAllFields(coffee.getClass(), fieldList);
        return fieldList;
    }

    private void getAllFields(Class<?> coffee, ArrayList<Field> fields) {
        for (Field field : coffee.getDeclaredFields()) {
            if (field.isAnnotationPresent(FieldInfo.class)) {
                if (field.getAnnotation(FieldInfo.class).isRepresented()) {
                    fields.add(field);
                }
            }
        }

        if (coffee.getSuperclass() != null) {
            getAllFields(coffee.getSuperclass(), fields);
        }
    }
}
