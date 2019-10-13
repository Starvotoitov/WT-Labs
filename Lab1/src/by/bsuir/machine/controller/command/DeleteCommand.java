package by.bsuir.machine.controller.command;

import by.bsuir.machine.service.DataManager;
import by.bsuir.machine.service.ServiceFactory;
import by.bsuir.machine.view.ViewFactory;
import by.bsuir.machine.view.ViewManager;

public class DeleteCommand implements Command {
    @Override
    public boolean execute(String[] args) {
        ViewFactory viewFactory = ViewFactory.getInstance();
        ViewManager viewManager = viewFactory.getViewManager();
        if (args.length == 3) {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            DataManager dataManager = serviceFactory.getDataManager();
            switch (args[1]) {
                case "order":
                    try {
                        dataManager.deleteOrder(Integer.parseInt(args[2]) - 1);
                        viewManager.showMessage("Заказ успешно удален");
                    } catch (NumberFormatException ex) {
                        viewManager.showErrorMessage("Неверные параметры");
                    }
                    break;
                case "profile":
                    try {
                        dataManager.deleteProfile(Integer.parseInt(args[2]) - 1);
                        viewManager.showMessage("Профиль успешно удален");
                    } catch (NumberFormatException ex) {
                        viewManager.showMessage("Неверные параметры");
                    }
                    break;
                default:
                    viewManager.showMessage("Неверные параметры");
            }
        } else {
            viewManager.showMessage("Неверные параметры");
        }
        return true;
    }
}
