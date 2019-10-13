package by.bsuir.machine.controller.command;

import by.bsuir.machine.service.DataManager;
import by.bsuir.machine.service.ServiceFactory;
import by.bsuir.machine.view.ViewFactory;
import by.bsuir.machine.view.ViewManager;

public class ShowCommand implements Command {
    @Override
    public boolean execute(String[] args) {
        ViewFactory viewFactory = ViewFactory.getInstance();
        ViewManager viewManager = viewFactory.getViewManager();

        if ((args.length == 2) || ((args.length == 3) && (args[2].compareTo("current") == 0))) {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            DataManager dataManager = serviceFactory.getDataManager();
            switch (args[1]) {
                case "order":
                    if (args.length == 3) {
                        viewManager.showOrder(dataManager.getCurrentOrder());
                    } else {
                        viewManager.showOrderList(dataManager.getOrders());
                    }
                    break;
                case "profile":
                    if (args.length == 3) {
                        viewManager.showCurrentProfile(dataManager.getCurrentProfile());
                    } else {
                        viewManager.showProfileList(dataManager.getProfiles());
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
