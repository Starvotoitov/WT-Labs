package by.bsuir.machine.controller.command;

import by.bsuir.machine.service.DataManager;
import by.bsuir.machine.service.ServiceFactory;
import by.bsuir.machine.view.ViewFactory;
import by.bsuir.machine.view.ViewManager;

import java.io.IOException;

public class SaveToFileCommand implements Command {
    @Override
    public boolean execute(String[] args) {
        ViewFactory viewFactory = ViewFactory.getInstance();
        ViewManager viewManager = viewFactory.getViewManager();
        if (args.length == 2) {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            DataManager dataManager = serviceFactory.getDataManager();
            switch (args[1]) {
                case "order":
                    try {
                        dataManager.saveOrders();
                    } catch (IOException ex) {
                        viewManager.showErrorMessage("Не удалось сохранить заказы в файл");
                    }
                    break;
                case "profile":
                    try {
                        dataManager.saveProfiles();
                    } catch (IOException ex) {
                        viewManager.showErrorMessage("Не удалось сохранить профили в файл");
                    }
                    break;
                default:
                    viewManager.showErrorMessage("Неверные параметры");
            }
        } else {
            viewManager.showErrorMessage("Неверные параметры");
        }
        return true;
    }
}
