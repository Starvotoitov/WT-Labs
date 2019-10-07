package machine.controller;

import machine.service.order.Order;
import machine.service.DataManager;
import machine.view.ViewManager;

import java.io.IOException;
import java.util.Scanner;

public class ControlManager {
    private DataManager dataManager;
    private ViewManager viewManager;
    private Scanner in;

    public ControlManager() {
        dataManager = new DataManager();
        viewManager = new ViewManager();
        in = new Scanner(System.in);
    }

    public void start() {
        while (mainMenu());
    }

    private boolean mainMenu() {
        viewManager.showMainMenu();
        switch (in.nextLine()) {
            case "1":
                while (orderMenu());
                break;
            case "2":
                while (profileMenu());
                break;
            case "3":
                return false;
        }
        return true;
    }

    private boolean orderMenu() {
        viewManager.showOrderMenu();
        switch (in.nextLine()) {
            case "1":
                createNewOrder();
                break;
            case "2":
                showOrderList();
                break;
            case "3":
                changeOrderMenu();
                break;
            case "4":
                deleteOrder();
                break;
            case "5":
                sortOrders();
                break;
            case "6":
                saveOrders();
                saveProfiles();
                return false;
        }
        return true;
    }

    private void createNewOrder() {
        viewManager.showCoffeeList(dataManager.getCoffeeList());
        int coffeeIndex = scanNumber(1, dataManager.getCoffeeList().length) - 1;
        Order newOrder = dataManager.createOrder(coffeeIndex);
        updateOrder(newOrder);
        dataManager.addOrder(newOrder);
        if (dataManager.isCurrentProfileSet()) {
            viewManager.showChoose("Сохранить настройки?");
            if (scanNumber(1, 2) == 1) {
                dataManager.updateProfile(newOrder.getCoffee());
            }
        }
    }

    private void updateOrder(Order order) {
        boolean isUpdateContinue = true;
        while (isUpdateContinue) {
            viewManager.showOrder(dataManager.getCoffeeInfo(order.getCoffee()), order);
            viewManager.showChoose("Изменить настройки?");
            if (scanNumber(1, 2) == 1) {
                viewManager.showMessage("Номер настройки для изменения");
                int fieldIndex = scanNumber(1, dataManager.getCoffeeInfo(order.getCoffee()).size());
                viewManager.showMessage("Новое значение");
                int newVal = scanNumber(1, -1);
                try {
                    dataManager.updateOrder(order, fieldIndex, newVal);
                } catch (IllegalAccessException ex) {
                    viewManager.showErrorMessage("Неправильный ввод");
                }
            } else {
                isUpdateContinue = false;
            }
        }
    }

    private void showOrderList() {
        Order[] orders = dataManager.getOrders();
        for (int i = 0; i < orders.length; i++) {
            viewManager.showOrder(dataManager.getCoffeeInfo(orders[i].getCoffee()), orders[i], i + 1);
        }
    }

    private void changeOrderMenu() {
        showOrderList();
        viewManager.showMessage("Номер заказ для изменения");
        int orderIndex = scanNumber(1, dataManager.getOrdersCount());
        updateOrder(dataManager.getOrders()[orderIndex - 1]);
    }

    private void deleteOrder() {
        showOrderList();
        viewManager.showMessage("Номер заказа для удаления");
        int deleteIndex = scanNumber(1, dataManager.getOrdersCount());
        dataManager.deleteOrder(deleteIndex - 1);
    }

    private boolean profileMenu() {
        String currentProfileName = dataManager.getCurrentProfile();
        viewManager.showProfileMenu(currentProfileName != null ? currentProfileName : "Профиль не выбран");
        switch (in.nextLine()) {
            case "1":
                addProfile();
                break;
            case "2":
                deleteProfile();
                break;
            case "3":
                chooseProfile();
                break;
            case "4":
                sortProfiles();
                break;
            case "5":
                saveProfiles();
                return false;
        }
        return true;
    }

    private void addProfile() {
        viewManager.showMessage("Имя профиля");
        String name = in.nextLine();
        dataManager.createProfile(name);
    }

    private void deleteProfile() {
        viewManager.showProfileList(dataManager.getProfiles());
        if (dataManager.getProfileCount() > 0) {
            viewManager.showMessage("Номер профиля для удаления");
            int profileIndex = scanNumber(1, dataManager.getProfileCount());
            dataManager.deleteProfile(profileIndex - 1);
        }
    }

    private void chooseProfile() {
        viewManager.showProfileList(dataManager.getProfiles());
        if (dataManager.getProfileCount() > 0) {
            viewManager.showMessage("Номер профиля");
            int profileIndex = scanNumber(1, dataManager.getProfileCount());
            dataManager.chooseProfile(profileIndex - 1);
        }
    }

    private void sortOrders() {
        viewManager.showOrderSorts(dataManager.getOrderComparators());
        viewManager.showMessage("Номер сортировки");
        int comparatorIndex = scanNumber(1, dataManager.getOrderComparators().length);
        viewManager.showChoose("Выполнить обратную сортировку?");
        boolean isReversed = scanNumber(1, 2) == 1;
        dataManager.sortOrders(comparatorIndex - 1, isReversed);
    }

    private void sortProfiles() {
        viewManager.showProfileSorts(dataManager.getProfileComparators());
        viewManager.showMessage("Номер сортировки");
        int comparatorIndex = scanNumber(1, dataManager.getProfileComparators().length);
        viewManager.showChoose("Выполнить обратную сортировку?");
        boolean isReversed = scanNumber(1, 2) == 1;
        dataManager.sortProfiles(comparatorIndex, isReversed);
    }

    private void saveProfiles() {
        try {
            dataManager.saveProfiles();
        } catch (IOException ex) {
            viewManager.showErrorMessage("Не удалось сохранить профили в файл");
        }
    }

    private void saveOrders() {
        try {
            dataManager.saveOrders();
        } catch (IOException ex) {
            viewManager.showErrorMessage("Не удалось сохранить заказы в файл");
        }
    }

    private int scanNumber(int lowerBound, int higherBound) {
        int num = 0;
        boolean isCorrectInput = false;
        while (!isCorrectInput) {
            try {
                num = Integer.parseInt(in.nextLine());
                isCorrectInput = (num >= lowerBound) && ((num <= higherBound) || higherBound == -1);
            } catch (NumberFormatException ex) {
                viewManager.showErrorMessage("Недопустимый ввод");
            }
        }
        return num;
    }
}
