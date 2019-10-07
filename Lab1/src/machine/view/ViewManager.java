package machine.view;

import machine.annotation.CoffeeInfo;
import machine.annotation.ComparatorInfo;
import machine.annotation.FieldInfo;
import machine.service.coffee.Espresso;
import machine.service.order.Order;
import machine.service.profile.Profile;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;

public class ViewManager {
    public void showMainMenu() {
        System.out.println("1: Заказы");
        System.out.println("2: Профили");
        System.out.println("3: Выход");
    }

    public void showOrderMenu() {
        System.out.println("1: Добавить заказ");
        System.out.println("2: Показать заказ");
        System.out.println("3: Изменить заказ");
        System.out.println("4: Удалить заказ");
        System.out.println("5: Сортировать заказы");
        System.out.println("6: Назад");
    }

    public void showOrder(ArrayList<Field> fields, Order order) {
        System.out.println(order.getDate());
        System.out.println(getCoffeeName(order.getCoffee().getClass()));
        showCoffeeInfo(fields, order.getCoffee());
    }

    public void showOrder(ArrayList<Field> fields, Order order, int num) {
        System.out.println("----------" + num + "----------");
        System.out.println(order.getDate());
        System.out.println(getCoffeeName(order.getCoffee().getClass()));
        showCoffeeInfo(fields, order.getCoffee());
        System.out.println("---------------------");
    }

    public void showCoffeeList(Class<? extends Espresso>[] coffeeList) {
        for (int i = 0; i < coffeeList.length; i++) {
            System.out.printf("%d: %s\n", i + 1, getCoffeeName(coffeeList[i]));
        }
    }

    public void showCoffeeInfo(ArrayList<Field> coffeeInfo, Espresso coffee) {
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

    public void showChoose(String choose) {
        System.out.println(choose);
        System.out.println("1: Да");
        System.out.println("2: Нет");
    }

    public void showProfileMenu(String name) {
        System.out.println("Текущий профиль: " + name);
        System.out.println("1: Добавить профиль");
        System.out.println("2: Удалить профиль");
        System.out.println("3: Выбрать текущий профиль");
        System.out.println("4: Отсортировать профили");
        System.out.println("5: Назад");
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
}
