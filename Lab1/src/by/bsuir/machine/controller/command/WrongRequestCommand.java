package by.bsuir.machine.controller.command;

import by.bsuir.machine.view.ViewFactory;
import by.bsuir.machine.view.ViewManager;

public class WrongRequestCommand implements Command {
    @Override
    public boolean execute(String[] args) {
        ViewFactory viewFactory = ViewFactory.getInstance();
        ViewManager viewManager = viewFactory.getViewManager();
        viewManager.showErrorMessage("Неправильная команда");
        return true;
    }
}
