package by.bsuir.machine.controller.command;

import by.bsuir.machine.service.DataManager;
import by.bsuir.machine.service.ServiceFactory;
import by.bsuir.machine.view.ViewFactory;
import by.bsuir.machine.view.ViewManager;

public class ShowSortTypesCommand implements Command {
    @Override
    public boolean execute(String[] args) {
        ViewFactory viewFactory = ViewFactory.getInstance();
        ViewManager viewManager = viewFactory.getViewManager();
        if (args.length == 2) {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            DataManager dataManager = serviceFactory.getDataManager();
            switch (args[1]) {
                case "order":
                    viewManager.showOrderSorts(dataManager.getOrderComparators());
                    break;
                case "profile":
                    viewManager.showProfileSorts(dataManager.getProfileComparators());
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
