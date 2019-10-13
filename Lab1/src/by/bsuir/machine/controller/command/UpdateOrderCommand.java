package by.bsuir.machine.controller.command;

import by.bsuir.machine.service.DataManager;
import by.bsuir.machine.service.ServiceFactory;
import by.bsuir.machine.view.ViewFactory;
import by.bsuir.machine.view.ViewManager;

public class UpdateOrderCommand implements Command {
    @Override
    public boolean execute(String[] args) {
        ViewFactory viewFactory = ViewFactory.getInstance();
        ViewManager viewManager = viewFactory.getViewManager();
        if (args.length == 3) {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            DataManager dataManager = serviceFactory.getDataManager();
            try {
                dataManager.updateOrder(Integer.parseInt(args[1]) - 1, Integer.parseInt(args[2]));
                viewManager.showOrder(dataManager.getCurrentOrder());
            } catch (NumberFormatException | IllegalAccessException ex) {
                viewManager.showErrorMessage("Неверные параметры");
            }
        } else {
            viewManager.showErrorMessage("Неверные параметры");
        }
        return true;
    }
}
