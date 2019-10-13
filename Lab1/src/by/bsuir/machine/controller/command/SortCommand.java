package by.bsuir.machine.controller.command;

import by.bsuir.machine.service.DataManager;
import by.bsuir.machine.service.ServiceFactory;
import by.bsuir.machine.view.ViewFactory;
import by.bsuir.machine.view.ViewManager;

public class SortCommand implements Command {
    @Override
    public boolean execute(String[] args) {
        ViewFactory viewFactory = ViewFactory.getInstance();
        ViewManager viewManager = viewFactory.getViewManager();
        if ((args.length == 3) || ((args.length == 4) && (args[3].compareTo("reverse") == 0))) {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            DataManager dataManager = serviceFactory.getDataManager();

            boolean isReversed = (args.length == 4);
            switch (args[1]) {
                case "order":
                    try {
                        dataManager.sortOrders(Integer.parseInt(args[2]) - 1, isReversed);
                    } catch (NumberFormatException ex) {
                        viewManager.showErrorMessage("Неверные параметры");
                    }
                    break;
                case "profile":
                    try {
                        dataManager.sortProfiles(Integer.parseInt(args[2]) - 1, isReversed);
                    } catch (NumberFormatException ex) {
                        viewManager.showErrorMessage("Неверные параметры");
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
