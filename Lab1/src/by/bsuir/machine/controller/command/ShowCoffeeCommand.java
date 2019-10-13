package by.bsuir.machine.controller.command;

import by.bsuir.machine.service.DataManager;
import by.bsuir.machine.service.ServiceFactory;
import by.bsuir.machine.view.ViewFactory;
import by.bsuir.machine.view.ViewManager;

public class ShowCoffeeCommand implements Command {
    @Override
    public boolean execute(String[] args) {
        ViewFactory viewFactory = ViewFactory.getInstance();
        ViewManager viewManager = viewFactory.getViewManager();
        if (args.length == 1) {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            DataManager dataManager = serviceFactory.getDataManager();
            viewManager.showCoffeeList(dataManager.getCoffeeList());
        } else {
            viewManager.showErrorMessage("Неверные параметры");
        }
        return true;
    }
}
